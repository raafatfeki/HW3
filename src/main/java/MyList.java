import java.util.Arrays;

public class MyList {

	public static void main(String args[])
	{
		CLiParser argParser = new CLiParser(args);
		argParser.parse();

		MyList mylist = new MyList();
		Object key = argParser.getKey();
		Object[] inputList = argParser.getInputList();


		int ret = mylist.binSearch((Comparable[])inputList, (Comparable)key);

		System.out.println(ret);
	}

	@SuppressWarnings("unchecked")
	public int binSearch(Comparable[] aList, Comparable key)
	{
		if (aList.length == 0)
			return -1;

		int mid;
		int first = 0;
		int last = aList.length-1;

		for (int i = 0; i < aList.length/2; i++)
		{
			mid = first + (last - first)/2;

			if(aList[mid].compareTo(key) == 0)
				return 1;
			else if (aList[mid].compareTo(key) > 0)
				last = mid-1;
			else
				first = mid+1;
		}
		return 0;
	}
}

