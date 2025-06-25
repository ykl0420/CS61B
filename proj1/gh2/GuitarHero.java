package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero{

	private final static String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk, .;/' ";
	private final static int N = 37;

	private static double frequncyOf(int i) {
		return 440.0 * Math.pow(2, (i - 24.0) / 12.0);
	}

	public static void main(String[] args) {
		/* create two guitar strings, for concert A and C */

		GuitarString[] strings = new GuitarString[37];

		for (int i = 0; i < N; i++) {
			strings[i] = new GuitarString(frequncyOf(i));
		}

		while (true) {

			/* check if the user has typed a key; if so, process it */
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				int index = KEYBOARD.indexOf(key);
				if (index == -1) {
					continue;
				}
				strings[index].pluck();
			}

			/* compute the superposition of samples */
			double sample = 0;

			for (int i = 0; i < N; i++) {
				sample += strings[i].sample();
			}

			/* play the sample on standard audio */
			StdAudio.play(sample);

			/* advance the simulation of each guitar string by one step */
			for (int i = 0; i < N; i++) {
				strings[i].tic();
			}
		}
	}
}

