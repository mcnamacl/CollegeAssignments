import java.math.BigInteger;

public class Rational {

	private int numerator;
	private int denominator;

	public Rational(int numerator, int denominator) {
		this.numerator = numerator;
		if (denominator == 0) {
			throw new IllegalArgumentException("Denominator cannot be zero.");
		}
		this.denominator = denominator;
	}

	public Rational(int numerator) {
		this.numerator = numerator;
		this.denominator = 1;
	}

	public Rational add(Rational first) {
		int totalNumerator = (this.numerator * first.denominator) + (this.denominator * first.numerator);
		int totalDenominator = this.denominator * first.denominator;
		Rational total = new Rational(totalNumerator, totalDenominator);
		return total;
	}

	public Rational subtract(Rational first) {
		int totalNumerator = (this.denominator * first.numerator) - (this.numerator * first.denominator);
		int totalDenominator = this.denominator * first.denominator;
		Rational total = new Rational(totalNumerator, totalDenominator);
		return total;
	}

	public Rational multiply(Rational first) {
		int totalNumerator = this.numerator * first.numerator;
		int totalDenominator = this.denominator * first.denominator;
		Rational total = new Rational(totalNumerator, totalDenominator);
		return total;
	}

	public Rational divide(Rational first) {
		int totalNumerator = this.numerator * first.denominator;
		int totalDenominator = this.denominator * first.numerator;
		Rational total = new Rational(totalNumerator, totalDenominator);
		return total;
	}

	public boolean equals(Rational first) {
		return this.numerator*first.denominator == this.denominator*first.numerator;
	}

	public boolean isLessThan(Rational first) {
		return this.numerator*first.denominator > this.denominator*first.numerator;
	}

	public Rational simplify() {
		int divider = GCD(this);
		this.numerator = this.numerator / divider;
		this.denominator = this.denominator / divider;
		return this;
	}

	public int GCD(Rational total) {
		BigInteger numerator = BigInteger.valueOf(total.numerator);
		BigInteger denominator = BigInteger.valueOf(total.denominator);
		BigInteger gdc = numerator.gcd(denominator);
		return gdc.intValue();
	}

	public String toString() {
		return numerator + "/" + denominator;
	}

}
