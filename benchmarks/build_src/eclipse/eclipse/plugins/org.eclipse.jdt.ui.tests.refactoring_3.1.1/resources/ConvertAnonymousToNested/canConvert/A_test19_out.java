package p;
class A{
	private final class Inner extends A {
		private final int u;
		int l= 9;
		int p0= 2, k= u, k1= k;
		int l1= l+1, p, q= p+u;
		private Inner(int u) {
			super();
			this.u= u;
			k= u;
			q= p+u;
		}
	}

	void f(){
		final int u= 8;
		new Inner(u);
	}
}