//計算木を生成するコード

abstract class Node {
    abstract public void setOp(char c);
    abstract public void addLeft(Node n);
    abstract public void addRight(Node n);
    abstract public double calc();
    abstract public String displayNode();
}
class Num extends Node {
    public Num(double n){
	value = n;
    }
    private double value;
    @Override public void setOp(char c){}
    @Override public void addLeft(Node n){}
    @Override public void addRight(Node n){}
    @Override public double calc(){return value;}
    @Override public String displayNode(){return String.valueOf(value);}
}
class Op extends Node{
    public static Node connectToLeft(Node n){
	final Op result = new Op();
	result.left = n;
	return result;
    }
    public Op(){}
    private char op;
    private Node left;
    private Node right;
    @Override public void setOp(char c){
	op = c;
    }
    @Override public void addLeft(Node n){
	left = n;
    }
    @Override public void addRight(Node n){
	right = n;
    }
    @Override public double calc(){
	if (left!=null && right!=null) {
	 double leftOp  = left.calc();
	 double rightOp = right.calc();
	
	 if(op == '+') return leftOp + rightOp;
	 else if(op == '-') return leftOp - rightOp;
	 else if(op== '*') return leftOp * rightOp;
	 else if(op == '/') return leftOp / rightOp;
	 else return 0.0;
	}
	else{return 0.0;}
    }
    @Override public String displayNode(){
	if (left!=null && right!=null) {
	 String leftStr  = left.displayNode();
	 String rightStr = right.displayNode();
	 String str ="(" + leftStr + String.valueOf(op) + rightStr + ")";
 	 return str;
	}
	else{return String.valueOf(op);}
    }

}
