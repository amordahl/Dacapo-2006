package invalidSelection;


	public void foo() {
		synchronized(this) {/*[*/
			foo();
		}/*[*/
	}
}