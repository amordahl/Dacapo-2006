// Copyright (c) Corporation for National Research Initiatives
package org.python.core;
import java.util.Stack;

public class ThreadState {
    //public InterpreterState interp;
    public PySystemState systemState;
    public PyFrame frame;
    //public PyObject curexc_type, curexc_value, curexc_traceback;
    //public PyObject exc_type, exc_value, exc_traceback;
    public PyException exception;
    public Thread thread;
    public boolean tracing;
    public PyList reprStack = null;
    //public PyInstance initializingProxy = null;
    private Stack initializingProxies = null;

    public int compareStateNesting = 0;
    private PyDictionary compareStateDict;

    public int recursion_depth = 0;

    public PyInstance getInitializingProxy() {
        if (initializingProxies == null || initializingProxies.empty()) {
            return null;
        }
        return (PyInstance)initializingProxies.peek();
    }

    public void pushInitializingProxy(PyInstance proxy) {
        if (initializingProxies == null) {
            initializingProxies = new Stack();
        }
        initializingProxies.push(proxy);
    }

    public void popInitializingProxy() {
        if (initializingProxies == null || initializingProxies.empty()) {
            throw Py.RuntimeError("invalid initializing proxies state");
        }
        initializingProxies.pop();
    }

    public ThreadState(Thread t, PySystemState systemState) {
        thread = t;
        // Fake multiple interpreter states for now
        /*if (Py.interp == null) {
          Py.interp = InterpreterState.getInterpreterState();
          }*/
        this.systemState = systemState;
        //interp = Py.interp;
        tracing = false;
        //System.out.println("new thread state");
    }

    public boolean enterRepr(PyObject obj) {
        //if (reprStack == null) System.err.println("reprStack: null");
        //else System.err.println("reprStack: "+reprStack.__len__());
        if (reprStack == null) {
            reprStack = new PyList(new PyObject[] {obj});
            return true;
        }
        for(int i=reprStack.size()-1; i>=0; i--) {
            if (obj == reprStack.pyget(i))
                return false;
        }
        reprStack.append(obj);
        return true;
    }

    public void exitRepr(PyObject obj) {
        if (reprStack == null)
            return;

        for (int i = reprStack.size()-1; i>=0; i--) {
            if (reprStack.pyget(i) == obj) {
                reprStack.delRange(i, reprStack.size(), 1);
            }
        }
    }

    public PyDictionary getCompareStateDict() {
        if (compareStateDict == null)
            compareStateDict = new PyDictionary();
        return compareStateDict;
    }
}
