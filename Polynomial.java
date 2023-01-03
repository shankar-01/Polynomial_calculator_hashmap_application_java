import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;


public class Polynomial {
	// use either HashMap or TreeMap to represent a polynomial 
	private HashMap<Integer, Double> p;
	//private TreeMap<Integer, Double> p;
	
	public Polynomial(String st) {
		//add code
		p = new HashMap<Integer, Double>();
		polynomialParser(st);
	}
	
	//add auxiliary methods and/or constructors
	private void polynomialParser(String str){
		String coefficient = "";
		String exponent = "";
		boolean coe = true;
		boolean wv = false;
		String nstr = "";

		for (int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '(' || str.charAt(i) == ')'){
				if(i > 0 && str.charAt(i) == '('){
					nstr = nstr.substring(0, nstr.lastIndexOf("+"));
				}
				continue;
			}
			nstr += str.charAt(i);
		}
		str = nstr;
		for (int i=0; i<str.length(); i++){

			char c = str.charAt(i);
			if(c=='-' && i==0){
				coefficient = "-";
			}
			else if(coe && ((c>='0' && c<='9') || c == '.')){
				coefficient+=c;
			}
			else if(c=='^'){
				coe = false;
			}
			else if(!coe && c>='0' && c<='9'){
				exponent +=c;
			}
			else if(c=='-' || c=='+'){
				coe = true;
				if(coefficient.equals("")){
					coefficient = "1";
				}
				if(exponent.equals("") && wv){
					exponent = "1";
				}
				else if(exponent.equals("") && !wv){
					exponent = "0";
				}
				if(coefficient.charAt(0)!='-') {
					addInExpression(Double.parseDouble(coefficient), Integer.parseInt(exponent));


				}
				else {
					if (coefficient.equals("-"))
						coefficient += "1";
					addInExpression(Double.parseDouble(coefficient.substring(1)) * (-1), Integer.parseInt(exponent));


				}
				coefficient = "";
				exponent = "";

				if(c=='-'){
					coefficient = "-";
				}

				wv = false;
			}
			else if(c>='A' && c<='Z'){
				coe = false;
				wv = true;
			}
			if (i == str.length()-1){
				if(coefficient.equals("")){
					coefficient = "1";
				}
				if(exponent.equals("") && wv){
					exponent = "1";
				}
				else if(exponent.equals("") && !wv){
					exponent = "0";
				}
				addInExpression(Double.parseDouble(coefficient), Integer.parseInt(exponent));

			}
		}
	}
	private void addInExpression(double coe, int exp){
		if(p.get(exp) != null){
			p.put(exp, p.get(exp)+coe);
		}
		else{
			p.put(exp, coe);
		}
	}
	public Polynomial add(Polynomial q) {
		//add code
		Polynomial r = new Polynomial("");
		for (int k: q.p.keySet()) {
			if(p.get(k) != null){
				r.p.put(k, p.get(k) + q.p.get(k));
			}
			else{
				r.p.put(k, q.p.get(k));
			}
		}
		for (int k: p.keySet()) {
			if(r.p.get(k) != null){
				continue;
			}
			r.p.put(k, p.get(k));
		}
		return r;
	}
	
	public Polynomial subtract(Polynomial q) {
		//add code
		Polynomial r = new Polynomial("");
		for (int k: q.p.keySet()) {
			if(p.get(k) != null){
				r.p.put(k, p.get(k) - q.p.get(k));
			}
			else {
				r.p.put(k, -q.p.get(k));
			}
		}
		for (int k: p.keySet()) {
			if(r.p.get(k) != null){
				continue;
			}
			r.p.put(k, p.get(k));
		}
		return r;
	}
	
	public Polynomial multiply(Polynomial q) {
		//add code
		Polynomial r = new Polynomial("");
		Object[] key1 = q.p.keySet().toArray();
		for (int k: p.keySet()) {
			for(int i=0; i<key1.length; i++){
				int l = (Integer) key1[i];
				if(r.p.get(k + l) == null)
					r.p.put(k+ l, p.get(k) * q.p.get(l));
				else{
					r.p.put(k+l, p.get(k) * q.p.get(l) + r.p.get(k+l));
				}
			}
		}
		return r;
	}
	
	public String toString() {
		//add code
		String result = "";
		Object[] arr = p.keySet().toArray();
		int k = 0;
		for (int i=arr.length-1; i>= 0; i--) {
			k = (Integer) arr[i];
			if(p.get(k) == 0)
				continue;
			if(p.get(k) > 0){
				result += "+";
			}
			if(p.get(k) == -1 && k != 0){
				result += "-";
			}
			else if(p.get(k) != 1){
				result += p.get(k);
			}
			else if(k==0){
				result+=p.get(k);
			}
			if (k == 1)
				result += "X";
			else if(k > 1){
				result += "X^"+k;
			}
		}
		return result.charAt(0) == '-' ? result : result.substring(1);
	}
}
