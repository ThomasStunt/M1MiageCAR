/**
 * It's a parser we found and modify to parse the operations that were needed in the subject (here additions, subtractions, multiplications and divisions).
 */

public class ExpressionParser {
	public static double eval(final String str) throws RuntimeException {
		return new Object() {
			int pos = -1, ch;

			void nextChar() {
				this.ch = (++this.pos < str.length()) ? str.charAt(this.pos) : -1;
			}

			boolean eat(int charToEat) {
				while (this.ch == ' ')
					this.nextChar();
				if (this.ch == charToEat) {
					this.nextChar();
					return true;
				}
				return false;
			}

			double parse() {
				this.nextChar();
				double x = this.parseExpression();
				if (this.pos < str.length())
					throw new RuntimeException("Unexpected: " + (char) this.ch);
				return x;
			}

			// Grammar:
			// expression = term | expression `+` term | expression `-` term
			// term = factor | term `*` factor | term `/` factor
			// factor = `+` factor | `-` factor | `(` expression `)`
			// | number | functionName factor | factor `^` factor

			double parseExpression() {
				double x = this.parseTerm();
				for (;;) {
					if (this.eat('+'))
						x += this.parseTerm(); // addition
					else if (this.eat('-'))
						x -= this.parseTerm(); // subtraction
					else
						return x;
				}
			}

			double parseTerm() {
				double x = this.parseFactor();
				for (;;) {
					if (this.eat('*'))
						x *= this.parseFactor(); // multiplication
					else if (this.eat('/'))
						x /= this.parseFactor(); // division
					else
						return x;
				}
			}

			double parseFactor() {
				if (this.eat('+'))
					return this.parseFactor(); // unary plus

				double x;
				int startPos = this.pos;
				if (this.eat('(')) { // parentheses
					x = this.parseExpression();
					this.eat(')');
				} else if ((this.ch >= '0' && this.ch <= '9') || this.ch == '.') { // numbers
					while ((this.ch >= '0' && this.ch <= '9') || this.ch == '.')
						this.nextChar();
					x = Double.parseDouble(str.substring(startPos, this.pos));
				} else if (this.ch >= 'a' && this.ch <= 'z') { // functions
					while (this.ch >= 'a' && this.ch <= 'z')
						this.nextChar();
					String func = str.substring(startPos, this.pos);
					x = this.parseFactor();
					if (func.equals("sqrt"))
						x = Math.sqrt(x);
					else if (func.equals("sin"))
						x = Math.sin(Math.toRadians(x));
					else if (func.equals("cos"))
						x = Math.cos(Math.toRadians(x));
					else if (func.equals("tan"))
						x = Math.tan(Math.toRadians(x));
					else
						throw new RuntimeException("Unknown function: " + func);
				} else {
					throw new RuntimeException("Unexpected: " + (char) this.ch);
				}

				return x;
			}
		}.parse();
	}
}
