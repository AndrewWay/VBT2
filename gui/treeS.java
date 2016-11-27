package gui;

public class treeS {
	treeS left;
	treeS right;
	private treeS parent;
	//team at each stage 
	Team data;
	int id;
	
	public treeS(treeS left, treeS right, treeS parent, Team item) {
		this.left = left;
		this.right = right;
		this.parent = parent;
		this.data = item;
	}
	//standard constructor
	public treeS(){
		this.left = null;
		this.right = null;
		this.parent = null;
		this.data = null;
		this.id = 99;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public treeS getLeft() {
		return left;
	}

	public void setLeft(treeS left) {
		this.left = left;
	}

	public treeS getRight() {
		return right;
	}

	public void setRight(treeS right) {
		this.right = right;
	}

	public treeS getParent() {
		return parent;
	}

	public void setParent(treeS parent) {
		this.parent = parent;
	}

	public Team getData() {
		return data;
	}

	public void setData(Team data) {
		this.data = data;
	}
}
