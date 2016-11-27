
public class TreeS {
	TreeS left;
	TreeS right;
	private TreeS parent;
	//Team at each stage 
	Team data;
	
	public TreeS(TreeS left, TreeS right, TreeS parent, Team item) {
		this.left = left;
		this.right = right;
		this.parent = parent;
		this.data = item;
	}
	//standard constructor
	public TreeS(){
		this.left = null;
		this.right = null;
		this.parent = null;
		this.data = null;
	}

	public TreeS getLeft() {
		return left;
	}

	public void setLeft(TreeS left) {
		this.left = left;
	}

	public TreeS getRight() {
		return right;
	}

	public void setRight(TreeS right) {
		this.right = right;
	}

	public TreeS getParent() {
		return parent;
	}

	public void setParent(TreeS parent) {
		this.parent = parent;
	}

	public Team getData() {
		return data;
	}

	public void setData(Team data) {
		this.data = data;
	}
}
