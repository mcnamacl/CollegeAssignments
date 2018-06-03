import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HarnessRecords {
	private ArrayList<Harness> harnesses;
	public static final int MAKE = 0;
	public static final int MODEL_NUMBER = 1;
	public static final int INSTRUCTOR = 2;

	public HarnessRecords(ArrayList<Harness> harnesses) {
		harnesses = null;
	}

	public HarnessRecords() {
		harnesses = new ArrayList<Harness>();
		try {
			FileReader fileReader = new FileReader(
					"C:\\Users\\mcnam\\OneDrive - TCDUD.onmicrosoft.com\\assignment16\\Harnesses.txt");// Enter
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			boolean endOfFile = false;
			while (!endOfFile) {
				String line = bufferedReader.readLine();
				if (line != null) {
					String[] harnessData = line.split(",");
					String make = harnessData[MAKE];
					long modelNumber = Long.parseLong(harnessData[MODEL_NUMBER]);
					String instructor = harnessData[INSTRUCTOR];
					Harness harness = new Harness(make, modelNumber, instructor);
					harnesses.add(harness);
				} else {
					endOfFile = true;
				}
			}

			bufferedReader.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean isEmpty() {
		boolean result = false;
		if (harnesses.size() == 0) {
			result = true;
		}
		return result;
	}

	public void addHarness(Harness newHarness) {
		harnesses.add(newHarness);
	}

	public Harness findHarness(String make, long modelNumber) {
		Harness harness = null;
		for (int index = 0; index < harnesses.size(); index++) {
			if (harnesses.get(index).getMake().equals(make) && harnesses.get(index).getModelNumber() == modelNumber) {
				harness = harnesses.get(index);
			}
		}
		return harness;
	}

	public Harness checkHarness(String instructor, String make, long modelNumber) {
		Harness harness = findHarness(make, modelNumber);
		if (harness != null && !harness.isHarnessOnLoan()) {
			harness.checkHarness(instructor);
		}
		return harness;
	}

	public Harness loanHarness(String borrower) {
		Harness harness = null;
		boolean foundAvailableHarness = false;
		for (int index = 0; index < harnesses.size() && !foundAvailableHarness; index++) {
			if (!harnesses.get(index).isHarnessOnLoan()) {
				harness = harnesses.get(index);
				harness.loanHarness(borrower);
				foundAvailableHarness = true;
			}
		}
		return harness;
	}

	public Harness returnHarness(String make, long modelNumber) {
		Harness harness = findHarness(make, modelNumber);
		if (harness != null && !harness.isHarnessOnLoan()) {
			harness.returnHarness();
		}
		return harness;
	}

	public Harness removeHarness(String make, long modelNumber) {
		Harness harness = findHarness(make, modelNumber);
		if (harness != null) {
			harnesses.remove(harness);
		}
		return harness;
	}

	public ArrayList<Harness> getHarnesses() {
		return harnesses;
	}
}
