import java.io.*;

public class TestDict {

    public static void main(String[] args) {
	final int TEXT = 2;
	BSTDictionary dictionary = new BSTDictionary();
	Record rec;
	Key key;

	String words[] = {"homework", "course", "class", "computer", "four"};
	String definitions[] = {"Very enjoyable work that students need to complete outside the classroom.",
				"A series of talks or lessons. For example, CS210.",
				"A set of students taught together,",
				"An electronic machine frequently used by Computer Science students.",
				"One more than three"};
	Key[] keys = new Key[5];
	Record[] records = new Record[5];
	
	boolean alltests = true;
	int test = -1;
	if (args.length > 0) {
		test = Integer.parseInt(args[0]);
		alltests = false;
	}
	
	for (int i = 0; i < 5; ++i) {
		keys[i] = new Key(words[i],TEXT);
		records[i] = new Record(keys[i],definitions[i]);
	}

	// Insert one word and then find it
	if (alltests || test == 1 || test == 3 || test == 6 || test == 7 || test == 8 || test == 9)
	try {
	    dictionary.put(records[0]);
	    rec = dictionary.get(keys[0]);
	    if ((rec.getDataItem()).compareTo(definitions[0]) == 0)
			System.out.println("Test 1 passed");
	    else System.out.println("Test 1 failed");
	}
	catch(Exception e) {
	    System.out.println("Test 1 failed");
	}