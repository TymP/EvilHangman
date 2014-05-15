public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Bookworm bookworm = new Bookworm();
		bookworm.read();
		bookworm.purgeForLength(5);
		UI ui = new UI();
		ui.printWords(bookworm.getWords());
		bookworm.partition('a');

		ui.printWords(bookworm.getWords());
	}
}
