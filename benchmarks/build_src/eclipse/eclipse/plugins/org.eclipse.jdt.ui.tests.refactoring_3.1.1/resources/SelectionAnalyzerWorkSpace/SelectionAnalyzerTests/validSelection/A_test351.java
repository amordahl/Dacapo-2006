package validSelection;


	public void foo() {
		foo();/*[*/
		synchronized (this) {
			foo();
		}
		/*]*/foo();
	}
}