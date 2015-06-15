package benchmark;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JPanel;

import dna.ArrayListDNAStrand;
import dna.DnaStrand;
import dna.FastStringDNAStrand;
import dna.LinkedListDNAStrand;
import dna.SlowStringDNAStrand;

/**
 * A benchmark program for DNA splicing.
 * 
 * @author Owen Astrachan
 * @author Barbara Lerner 
 * 
 */
public class DNABenchMark extends JPanel {
	// Number of splice trials to benchmark.
	private static final int NUM_TRIALS = 32;

	// Number of times to repeat each trial.
	private static final int NUM_REPEATS = 3;

	// Maximum time to go before quitting the bench mark.
	private static final double MAX_TIME = .5 * 60; // 30 seconds

	// The minimum length of the dna being spliced in.
	private static final int MINIMUM_SPLICE_STRING_LENGTH = 256;

	// The DNA sequence for the restriction enzyme used to split the DNA.
	private static final String ENZYME = "gaattc";

	// The file containing the DNA this program will manipulate
	private static final String DNA_FILE = "ecolishort.dat";

	// The file to write the experiment results to.
	private static final String RESULT_FILE = "LinkedListResults.csv";

	/**
	 * Creates and runs a benchmark.
	 */
	public DNABenchMark() {
		// Read DNA from a file
		String dnaSource;
		try {
			dnaSource = dnaFromScanner(new Scanner(new File(DNA_FILE)));
			System.out.println("dna length = " + dnaSource.length());

			// Construct the minimum length splice string
			String spliceString = constructSpliceeString();

			// Run the experiments.  
			runAllExperiments(dnaSource, spliceString);
		} catch (FileNotFoundException e2) {
			System.out.println("Could not open " + DNA_FILE);
		}
	}

	/**
	 * Build the string to use for the splicee
	 * @return the splicee string
	 */
	private String constructSpliceeString() {
		StringBuilder spliceString = new StringBuilder();
		for (int i = 0; i < MINIMUM_SPLICE_STRING_LENGTH; i++) {
			spliceString = spliceString.append("c");
		}
		return spliceString.toString();
	}

	/**
	 * Run all experiments.  Each experiment runs until it either runs out of memory, a trial
	 * run takes more than the maximum time allowed, or all trials complete.
	 * @param dnaSource the DNA to operate on
	 * @param spliceString the splicee string
	 */
	private void runAllExperiments(String dnaSource, String spliceString) {
		PrintWriter resultsFile = null;
		try {
			// The result file will be a comma-separated values file (a CSV file) suitable for
			// loading into a spreadsheet, like Apple's Numbers, or Microsoft Excel.
			resultsFile = new PrintWriter(new File(RESULT_FILE));
			try {
				resultsFile.println("DNA Length Experiment");
				resultsFile
						.println("Enzyme length, Splicee length,Original DNA length,Recomb DNA length,Time");

				runVaryDnaLengthExperiment(spliceString, dnaSource,
						resultsFile);
			} catch (Throwable e) {
				System.out.println(e.getClass().getName());
				System.gc();
			}
			
			try {
				resultsFile.println("\nEnzyme Length Experiment");
				resultsFile
						.println("Enzyme length, Splicee length,Original DNA length,Recomb DNA length,Time");

				runVaryEnzymeLengthExperiment(spliceString, dnaSource,
						resultsFile);
			} catch (Throwable e) {
				System.out.println(e.getClass().getName());
				System.gc();
			}

			try {
				resultsFile.println("\nSplice Length Experiment");
				resultsFile
						.println("Enzyme length, Splicee length,Original DNA length,Recomb DNA length,Time");

				runSpliceLengthExperiment(spliceString, dnaSource,
						resultsFile);
			} catch (Throwable e) {
				System.out.println(e.getClass().getName());
			}
		} catch (FileNotFoundException e1) {
			System.out.println("Could not open " + RESULT_FILE);
		} finally {
			// The finally clause is executed after the try block, whether an exception is thrown or not.
			if (resultsFile != null) {
				resultsFile.close();
			}
		}
	}

	/**
	 * Return a string representing the DNA read from the scanner, ignoring any
	 * characters that can't be part of DNA and converting all characters to
	 * lower case.
	 * 
	 * @param s
	 *            is the Scanner read from
	 * @return a string representing the DNA read, characters in the returned
	 *         string are restricted to 'c', 'g', 't', 'a'
	 */
	private String dnaFromScanner(Scanner s) {
		assert s != null;

		// StringBuilder is more efficient than String when building up
		// a String value from smaller parts.
		StringBuilder buf = new StringBuilder();
		while (s.hasNextLine()) {
			String line = s.nextLine().toLowerCase();
			for (int k = 0; k < line.length(); k++) {
				char ch = line.charAt(k);

				// Only include the character if it is a valid DNA character (a,
				// c, g or t)
				if ("acgt".indexOf(ch) != -1) {
					buf.append(ch);
				}
			}
		}

		// Returns the string that was built.
		return buf.toString();
	}

	/**
	 * Performs a strand splice and times how long it takes.
	 * 
	 * @param enzyme
	 *            the DNA of the enzyme that splits the long strand of DNA
	 * @param splicee
	 *            the DNA to splice into the split
	 * @param strand
	 *            the DNA strand being manipulated
	 * @throws OverTimeException
	 *             if a benchamrk took longer than the maximum allowed time
	 */
	private void strandSpliceBenchmark(String enzyme, String splicee,
			String dnaSource, PrintWriter resultsFile) throws OverTimeException {
		double totalTime = 0;  // Total time for all trials.
		long length = 0;  // Length of the original DNA
		long recLength = 0;  // Length of the recombinant DNA
		double time = 0;  // Time of the last trial executed.

		int repeats;
		
		// Stop when we complete the number of trials we want or one trial takes too long.
		for (repeats = 1; repeats <= NUM_REPEATS && time < MAX_TIME; repeats++) {
			// Create the strand to operate on
			DnaStrand strand;
			
			// Uncomment exactly 1 of the following 3 lines to benchmark the different
			// implementations.
//			strand = new FastStringDNAStrand(dnaSource);
//			strand = new ArrayListDNAStrand(dnaSource);
//			 strand = new SlowStringDNAStrand(dnaSource);
			strand = new LinkedListDNAStrand(dnaSource);

			// Remember the length of the strand before we start
			length = strand.size();

			// Start a timer
			double start = System.currentTimeMillis();

			// Do the experiment
			DnaStrand recomb = strand.cutAndSplice(enzyme, splicee);

			// Stop the timer and determine how long it took in seconds.
			double end = System.currentTimeMillis();
			time = (end - start) / 1000.0;
			totalTime = totalTime + time;

			// Return information about the experiment.
			long length2 = strand.size();
			assert length == length2;  // Check that the original DNA was not modified.
			recLength = recomb.size();
			
			// Output result of each trial to the console
			System.out
					.println(String
							.format(
									"%s:\t enzyme %,d\t splicee %,d\t time %1.3f\tbefore %,d\tafter %,d\trecomb %,d", 
									strand.getClass().getName(), enzyme
											.length(), splicee.length(), time,
									length, length2, recLength));
		}

		// Save the average of all trials to the results file.
		double averageTime = totalTime / repeats;
		resultsFile.println("" + enzyme.length() + "," + splicee.length() + ","
				+ length + "," + recLength + "," + averageTime);

		if (time >= MAX_TIME) {
			throw new OverTimeException();
		}

	}

	/**
	 * Runs an experiment in which the only change is that the splice string
	 * gets longer with each trial.
	 * 
	 * @param spliceString
	 *            the string to splice in.
	 * @param dnaSource
	 *            the DNA sequence
	 * @throws OverTimeException
	 *             if an execution took longer than the maximum allowed time
	 */
	private void runSpliceLengthExperiment(String spliceString,
			String dnaSource, PrintWriter resultsFile) throws OverTimeException {
		
		System.out.println("\n\n*** Splice length experiment ***");
		// Repeatedly splice in strings of c's in increasing lengths
		StringBuilder trialSpliceString = new StringBuilder(spliceString);
		for (int trial = 1; trial <= NUM_TRIALS; trial++) {

			strandSpliceBenchmark(ENZYME, trialSpliceString.toString(), dnaSource,
					resultsFile);
			System.out.println();

			// Increase the length of the string
			trialSpliceString = trialSpliceString.append(trialSpliceString.toString());
		}
	}

	/**
	 * Runs an experiment in which the length of the enzyme changes. This
	 * affects how many places the strand gets broken.
	 * 
	 * @param spliceString
	 *            the string being spliced in
	 * @param dnaSource
	 *            the DNA sequence
	 * @throws OverTimeException
	 *             if an execution took longer than the maximum allowed time
	 */
	private void runVaryEnzymeLengthExperiment(String spliceString,
			String dnaSource, PrintWriter resultsFile) throws OverTimeException {
		System.out.println("\n\n*** Enzyme length experiment ***");
		// Repeatedly search for enzymes of decreasing length
		for (int enzymeLength = ENZYME.length(); enzymeLength > 0; enzymeLength--) {

			strandSpliceBenchmark(ENZYME.substring(0, enzymeLength),
					spliceString, dnaSource, resultsFile);
			System.out.println();
		}
	}

	/**
	 * Runs an experiment in which the length of the DNA changes.
	 * 
	 * @param spliceString
	 *            the string to splice in
	 * @param resultsFile
	 * @param strand
	 *            the original DNA strand
	 * @throws OverTimeException
	 *             if an execution took longer than the maximum allowed time
	 */
	private void runVaryDnaLengthExperiment(String spliceString,
			String dnaSource, PrintWriter resultsFile) throws OverTimeException {
		System.out.println("\n\n*** DNA length experiment ***");
		// Repeatedly splice in increasing the length of the DNA on each trial

		StringBuilder trialDNA = new StringBuilder(dnaSource);
		for (int trial = 1; trial <= NUM_TRIALS; trial++) {

			strandSpliceBenchmark(ENZYME, spliceString, trialDNA.toString(), resultsFile);
			System.out.println();

			// Increase the length of the string
			trialDNA = trialDNA.append(trialDNA.toString());
		}
	}

	/**
	 * Runs the benchmark program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new DNABenchMark();

	}

}
