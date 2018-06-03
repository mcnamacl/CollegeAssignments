public class Harness {

	public static final int MAX_NUMBER_OF_TIMES = 25;
	private String make; 
	private long modelNumber; 
	private int noOfTimesUsed;
	private String instructor;
	private boolean harnessOnLoan; 
	private String borrower;
	
	public Harness(String make, long modelNumber, int noOfTimesUsed, String instructor, boolean harnessOnLoan,
			String borrower) {
		this.make = make;
		this.modelNumber = modelNumber;
		this.noOfTimesUsed = noOfTimesUsed;
		this.instructor = instructor;
		this.harnessOnLoan = harnessOnLoan;
		this.borrower = borrower;
	}
	
	public Harness(String make, long modelNumber, String instructor) {
		this.make = make;
		this.modelNumber = modelNumber;
		this.instructor = instructor;
		noOfTimesUsed = 0;
		harnessOnLoan = false; 
		borrower = null;
	}
	
	public void checkHarness(String newInstructor) {
		noOfTimesUsed = 0;
		if (!harnessOnLoan && newInstructor != null) {
			instructor = newInstructor;
		}
	}
	
	public void loanHarness(String newBorrower) {
		if (canHarnessBeLoaned() == true) {
			harnessOnLoan = true;
			borrower = newBorrower;
			noOfTimesUsed++;
		}
	}
	
	public void returnHarness() {
		if (harnessOnLoan == true) {
			harnessOnLoan = false;
			borrower = null;
		}
	}
	
	public boolean canHarnessBeLoaned() {
		boolean result = false;
		if (noOfTimesUsed < MAX_NUMBER_OF_TIMES && harnessOnLoan == false) {
			result = true;
		}
		return result;
	}

	public String getMake() {
		return make;
	}

	public long getModelNumber() {
		return modelNumber;
	}

	public int getNoOfTimesUsed() {
		return noOfTimesUsed;
	}

	public String getInstructor() {
		return instructor;
	}

	public boolean isHarnessOnLoan() {
		return harnessOnLoan;
	}

	public String getBorrower() {
		return borrower;
	}

	@Override
	public String toString() {
		return "Harness [make=" + make + ", modelNumber=" + modelNumber + ", noOfTimesUsed=" + noOfTimesUsed
				+ ", instructor=" + instructor + ", harnessOnLoan=" + harnessOnLoan + ", borrower=" + borrower + "]";
	}

}
