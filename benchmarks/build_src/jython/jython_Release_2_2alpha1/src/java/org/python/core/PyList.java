// Copyright (c) Corporation for National Research Initiatives

// Implementation of the standard Python list objects

package org.python.core;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * A builtin python list.
 */

public class PyList extends PySequenceList {

    public static void classDictInit(PyObject dict) throws PyIgnoreMethodTag {}

    /* type info */

    public static final String exposed_name="list";

    public static final Class exposed_base=PyObject.class;

    public static void typeSetup(PyObject dict,PyType.Newstyle marker) {
        class exposed___ne__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___ne__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___ne__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                PyObject ret=self.seq___ne__(arg0);
                if (ret==null)
                    return Py.NotImplemented;
                return ret;
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                PyObject ret=self.seq___ne__(arg0);
                if (ret==null)
                    return Py.NotImplemented;
                return ret;
            }

        }
        dict.__setitem__("__ne__",new PyMethodDescr("__ne__",PyList.class,1,1,new exposed___ne__(null,null)));
        class exposed___eq__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___eq__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___eq__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                PyObject ret=self.seq___eq__(arg0);
                if (ret==null)
                    return Py.NotImplemented;
                return ret;
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                PyObject ret=self.seq___eq__(arg0);
                if (ret==null)
                    return Py.NotImplemented;
                return ret;
            }

        }
        dict.__setitem__("__eq__",new PyMethodDescr("__eq__",PyList.class,1,1,new exposed___eq__(null,null)));
        class exposed___getitem__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___getitem__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___getitem__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                PyObject ret=self.seq___finditem__(arg0);
                if (ret==null) {
                    throw Py.IndexError("index out of range: "+arg0);
                }
                return ret;
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                PyObject ret=self.seq___finditem__(arg0);
                if (ret==null) {
                    throw Py.IndexError("index out of range: "+arg0);
                }
                return ret;
            }

        }
        dict.__setitem__("__getitem__",new PyMethodDescr("__getitem__",PyList.class,1,1,new exposed___getitem__(null,null)));
        class exposed___contains__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___contains__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___contains__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                return Py.newBoolean(self.object___contains__(arg0));
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                return Py.newBoolean(self.object___contains__(arg0));
            }

        }
        dict.__setitem__("__contains__",new PyMethodDescr("__contains__",PyList.class,1,1,new exposed___contains__(null,null)));
        class exposed___delitem__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___delitem__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___delitem__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                self.seq___delitem__(arg0);
                return Py.None;
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                self.seq___delitem__(arg0);
                return Py.None;
            }

        }
        dict.__setitem__("__delitem__",new PyMethodDescr("__delitem__",PyList.class,1,1,new exposed___delitem__(null,null)));
        class exposed___setitem__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___setitem__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___setitem__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0,PyObject arg1) {
                self.seq___setitem__(arg0,arg1);
                return Py.None;
            }

            public PyObject inst_call(PyObject gself,PyObject arg0,PyObject arg1) {
                PyList self=(PyList)gself;
                self.seq___setitem__(arg0,arg1);
                return Py.None;
            }

        }
        dict.__setitem__("__setitem__",new PyMethodDescr("__setitem__",PyList.class,2,2,new exposed___setitem__(null,null)));
        class exposed___nonzero__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___nonzero__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___nonzero__((PyList)self,info);
            }

            public PyObject __call__() {
                return Py.newBoolean(self.seq___nonzero__());
            }

            public PyObject inst_call(PyObject gself) {
                PyList self=(PyList)gself;
                return Py.newBoolean(self.seq___nonzero__());
            }

        }
        dict.__setitem__("__nonzero__",new PyMethodDescr("__nonzero__",PyList.class,0,0,new exposed___nonzero__(null,null)));
        class exposed___getslice__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___getslice__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___getslice__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0,PyObject arg1,PyObject arg2) {
                return self.seq___getslice__(arg0,arg1,arg2);
            }

            public PyObject inst_call(PyObject gself,PyObject arg0,PyObject arg1,PyObject arg2) {
                PyList self=(PyList)gself;
                return self.seq___getslice__(arg0,arg1,arg2);
            }

        }
        dict.__setitem__("__getslice__",new PyMethodDescr("__getslice__",PyList.class,3,3,new exposed___getslice__(null,null)));
        class exposed___delslice__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___delslice__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___delslice__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0,PyObject arg1,PyObject arg2) {
                self.seq___delslice__(arg0,arg1,arg2);
                return Py.None;
            }

            public PyObject inst_call(PyObject gself,PyObject arg0,PyObject arg1,PyObject arg2) {
                PyList self=(PyList)gself;
                self.seq___delslice__(arg0,arg1,arg2);
                return Py.None;
            }

        }
        dict.__setitem__("__delslice__",new PyMethodDescr("__delslice__",PyList.class,3,3,new exposed___delslice__(null,null)));
        class exposed___setslice__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___setslice__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___setslice__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0,PyObject arg1,PyObject arg2,PyObject arg3) {
                self.seq___setslice__(arg0,arg1,arg2,arg3);
                return Py.None;
            }

            public PyObject inst_call(PyObject gself,PyObject arg0,PyObject arg1,PyObject arg2,PyObject arg3) {
                PyList self=(PyList)gself;
                self.seq___setslice__(arg0,arg1,arg2,arg3);
                return Py.None;
            }

        }
        dict.__setitem__("__setslice__",new PyMethodDescr("__setslice__",PyList.class,4,4,new exposed___setslice__(null,null)));
        class exposed_append extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed_append(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed_append((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                self.list_append(arg0);
                return Py.None;
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                self.list_append(arg0);
                return Py.None;
            }

        }
        dict.__setitem__("append",new PyMethodDescr("append",PyList.class,1,1,new exposed_append(null,null)));
        class exposed_count extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed_count(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed_count((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                return Py.newInteger(self.list_count(arg0));
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                return Py.newInteger(self.list_count(arg0));
            }

        }
        dict.__setitem__("count",new PyMethodDescr("count",PyList.class,1,1,new exposed_count(null,null)));
        class exposed_extend extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed_extend(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed_extend((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                self.list_extend(arg0);
                return Py.None;
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                self.list_extend(arg0);
                return Py.None;
            }

        }
        dict.__setitem__("extend",new PyMethodDescr("extend",PyList.class,1,1,new exposed_extend(null,null)));
        class exposed_index extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed_index(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed_index((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0,PyObject arg1,PyObject arg2) {
                try {
                    return Py.newInteger(self.list_index(arg0,arg1.asInt(1),arg2.asInt(2)));
                } catch (PyObject.ConversionException e) {
                    String msg;
                    switch (e.index) {
                    case 1:
                    case 2:
                        msg="expected an integer";
                        break;
                    default:
                        msg="xxx";
                    }
                    throw Py.TypeError(msg);
                }
            }

            public PyObject inst_call(PyObject gself,PyObject arg0,PyObject arg1,PyObject arg2) {
                PyList self=(PyList)gself;
                try {
                    return Py.newInteger(self.list_index(arg0,arg1.asInt(1),arg2.asInt(2)));
                } catch (PyObject.ConversionException e) {
                    String msg;
                    switch (e.index) {
                    case 1:
                    case 2:
                        msg="expected an integer";
                        break;
                    default:
                        msg="xxx";
                    }
                    throw Py.TypeError(msg);
                }
            }

            public PyObject __call__(PyObject arg0,PyObject arg1) {
                try {
                    return Py.newInteger(self.list_index(arg0,arg1.asInt(1)));
                } catch (PyObject.ConversionException e) {
                    String msg;
                    switch (e.index) {
                    case 1:
                        msg="expected an integer";
                        break;
                    default:
                        msg="xxx";
                    }
                    throw Py.TypeError(msg);
                }
            }

            public PyObject inst_call(PyObject gself,PyObject arg0,PyObject arg1) {
                PyList self=(PyList)gself;
                try {
                    return Py.newInteger(self.list_index(arg0,arg1.asInt(1)));
                } catch (PyObject.ConversionException e) {
                    String msg;
                    switch (e.index) {
                    case 1:
                        msg="expected an integer";
                        break;
                    default:
                        msg="xxx";
                    }
                    throw Py.TypeError(msg);
                }
            }

            public PyObject __call__(PyObject arg0) {
                return Py.newInteger(self.list_index(arg0));
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                return Py.newInteger(self.list_index(arg0));
            }

        }
        dict.__setitem__("index",new PyMethodDescr("index",PyList.class,1,3,new exposed_index(null,null)));

        class exposed_insert extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed_insert(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed_insert((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0,PyObject arg1) {
                try {
                    self.list_insert(arg0.asInt(0),arg1);
                    return Py.None;
                } catch (PyObject.ConversionException e) {
                    String msg;
                    switch (e.index) {
                    case 0:
                        msg="expected an integer";
                        break;
                    default:
                        msg="xxx";
                    }
                    throw Py.TypeError(msg);
                }
            }

            public PyObject inst_call(PyObject gself,PyObject arg0,PyObject arg1) {
                PyList self=(PyList)gself;
                try {
                    self.list_insert(arg0.asInt(0),arg1);
                    return Py.None;
                } catch (PyObject.ConversionException e) {
                    String msg;
                    switch (e.index) {
                    case 0:
                        msg="expected an integer";
                        break;
                    default:
                        msg="xxx";
                    }
                    throw Py.TypeError(msg);
                }
            }

        }
        dict.__setitem__("insert",new PyMethodDescr("insert",PyList.class,2,2,new exposed_insert(null,null)));
        class exposed_pop extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed_pop(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed_pop((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                try {
                    return self.list_pop(arg0.asInt(0));
                } catch (PyObject.ConversionException e) {
                    String msg;
                    switch (e.index) {
                    case 0:
                        msg="expected an integer";
                        break;
                    default:
                        msg="xxx";
                    }
                    throw Py.TypeError(msg);
                }
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                try {
                    return self.list_pop(arg0.asInt(0));
                } catch (PyObject.ConversionException e) {
                    String msg;
                    switch (e.index) {
                    case 0:
                        msg="expected an integer";
                        break;
                    default:
                        msg="xxx";
                    }
                    throw Py.TypeError(msg);
                }
            }

            public PyObject __call__() {
                return self.list_pop();
            }

            public PyObject inst_call(PyObject gself) {
                PyList self=(PyList)gself;
                return self.list_pop();
            }

        }
        dict.__setitem__("pop",new PyMethodDescr("pop",PyList.class,0,1,new exposed_pop(null,null)));
        class exposed_remove extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed_remove(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed_remove((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                self.list_remove(arg0);
                return Py.None;
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                self.list_remove(arg0);
                return Py.None;
            }

        }
        dict.__setitem__("remove",new PyMethodDescr("remove",PyList.class,1,1,new exposed_remove(null,null)));
        class exposed_reverse extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed_reverse(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed_reverse((PyList)self,info);
            }

            public PyObject __call__() {
                self.list_reverse();
                return Py.None;
            }

            public PyObject inst_call(PyObject gself) {
                PyList self=(PyList)gself;
                self.list_reverse();
                return Py.None;
            }

        }
        dict.__setitem__("reverse",new PyMethodDescr("reverse",PyList.class,0,0,new exposed_reverse(null,null)));
        class exposed_sort extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed_sort(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed_sort((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                self.list_sort(arg0);
                return Py.None;
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                self.list_sort(arg0);
                return Py.None;
            }

            public PyObject __call__() {
                self.list_sort();
                return Py.None;
            }

            public PyObject inst_call(PyObject gself) {
                PyList self=(PyList)gself;
                self.list_sort();
                return Py.None;
            }

        }
        dict.__setitem__("sort",new PyMethodDescr("sort",PyList.class,0,1,new exposed_sort(null,null)));
        class exposed___len__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___len__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___len__((PyList)self,info);
            }

            public PyObject __call__() {
                return Py.newInteger(self.list___len__());
            }

            public PyObject inst_call(PyObject gself) {
                PyList self=(PyList)gself;
                return Py.newInteger(self.list___len__());
            }

        }
        dict.__setitem__("__len__",new PyMethodDescr("__len__",PyList.class,0,0,new exposed___len__(null,null)));
        class exposed___add__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___add__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___add__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                return self.list___add__(arg0);
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                return self.list___add__(arg0);
            }

        }
        dict.__setitem__("__add__",new PyMethodDescr("__add__",PyList.class,1,1,new exposed___add__(null,null)));
        class exposed___radd__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___radd__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___radd__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                return self.list___radd__(arg0);
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                return self.list___radd__(arg0);
            }

        }
        dict.__setitem__("__radd__",new PyMethodDescr("__radd__",PyList.class,1,1,new exposed___radd__(null,null)));
        class exposed___iadd__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___iadd__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___iadd__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                return self.list___iadd__(arg0);
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                return self.list___iadd__(arg0);
            }

        }
        dict.__setitem__("__iadd__",new PyMethodDescr("__iadd__",PyList.class,1,1,new exposed___iadd__(null,null)));
        class exposed___imul__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___imul__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___imul__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                return self.list___imul__(arg0);
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                return self.list___imul__(arg0);
            }

        }
        dict.__setitem__("__imul__",new PyMethodDescr("__imul__",PyList.class,1,1,new exposed___imul__(null,null)));
        class exposed___mul__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___mul__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___mul__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                return self.list___mul__(arg0);
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                return self.list___mul__(arg0);
            }

        }
        dict.__setitem__("__mul__",new PyMethodDescr("__mul__",PyList.class,1,1,new exposed___mul__(null,null)));
        class exposed___rmul__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___rmul__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___rmul__((PyList)self,info);
            }

            public PyObject __call__(PyObject arg0) {
                return self.list___rmul__(arg0);
            }

            public PyObject inst_call(PyObject gself,PyObject arg0) {
                PyList self=(PyList)gself;
                return self.list___rmul__(arg0);
            }

        }
        dict.__setitem__("__rmul__",new PyMethodDescr("__rmul__",PyList.class,1,1,new exposed___rmul__(null,null)));
        class exposed___hash__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___hash__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___hash__((PyList)self,info);
            }

            public PyObject __call__() {
                return Py.newInteger(self.list_hashCode());
            }

            public PyObject inst_call(PyObject gself) {
                PyList self=(PyList)gself;
                return Py.newInteger(self.list_hashCode());
            }

        }
        dict.__setitem__("__hash__",new PyMethodDescr("__hash__",PyList.class,0,0,new exposed___hash__(null,null)));
        class exposed___repr__ extends PyBuiltinFunctionNarrow {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___repr__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___repr__((PyList)self,info);
            }

            public PyObject __call__() {
                return new PyString(self.list_toString());
            }

            public PyObject inst_call(PyObject gself) {
                PyList self=(PyList)gself;
                return new PyString(self.list_toString());
            }

        }
        dict.__setitem__("__repr__",new PyMethodDescr("__repr__",PyList.class,0,0,new exposed___repr__(null,null)));
        class exposed___init__ extends PyBuiltinFunctionWide {

            private PyList self;

            public PyObject getSelf() {
                return self;
            }

            exposed___init__(PyList self,PyBuiltinFunction.Info info) {
                super(info);
                this.self=self;
            }

            public PyBuiltinFunction makeBound(PyObject self) {
                return new exposed___init__((PyList)self,info);
            }

            public PyObject inst_call(PyObject self,PyObject[]args) {
                return inst_call(self,args,Py.NoKeywords);
            }

            public PyObject __call__(PyObject[]args) {
                return __call__(args,Py.NoKeywords);
            }

            public PyObject __call__(PyObject[]args,String[]keywords) {
                self.list_init(args,keywords);
                return Py.None;
            }

            public PyObject inst_call(PyObject gself,PyObject[]args,String[]keywords) {
                PyList self=(PyList)gself;
                self.list_init(args,keywords);
                return Py.None;
            }

        }
        dict.__setitem__("__init__",new PyMethodDescr("__init__",PyList.class,-1,-1,new exposed___init__(null,null)));
        dict.__setitem__("__new__",new PyNewWrapper(PyList.class,"__new__",-1,-1) {

        public PyObject new_impl(boolean init,PyType subtype,PyObject[]args,String[]keywords) {
            PyList newobj;
            if (for_type==subtype) {
                newobj=new PyList();
                if (init)
                    newobj.list_init(args,keywords);
                   } else {
                       newobj=new PyListDerived(subtype);
                   }
                   return newobj;
               }

        });
    }

    public PyList() {
        this(Py.EmptyObjects);
    }

    public PyList(PyType type) {
        super(type);
    }

    public PyList(Collection c) {
        super(c);
    }

    // TODO: fix dependency so it can be removed.
    // Shouldn't be required (see PyList(Collection c), but test_re.py fails 
    // without it.  Probably used by reflection.
    public PyList(Vector v) {
        super(v);
    }    
    
    public PyList(PyObject[] elements) {
        super(elements);
    }

    public PyList(PyObject o) {
        this();
        PyObject iter = o.__iter__();
        for (PyObject item = null; (item = iter.__iternext__()) != null; ) {
            append(item);
        }
    }

    final void list_init(PyObject[] args,String[] kwds) {
        int nargs = args.length - kwds.length;
        if (nargs > 1) {
            throw PyBuiltinFunction.DefaultInfo.unexpectedCall(nargs,false,exposed_name,0,1);
        }
        if (nargs == 0) {
            return;
        }

        PyObject o = args[0];
        if (o instanceof PySequenceList) {
            PySequenceList p = (PySequenceList) o.__getslice__(Py.None, Py.None, Py.One);
            this.list = p.list;
        } else {
            PyObject iter = o.__iter__();
            for (PyObject item = null; (item = iter.__iternext__()) != null; ) {
                append(item);
            }
        }
    }

    public String safeRepr() throws PyIgnoreMethodTag {
        return "'list' object";
    }

    public int __len__() {
        return list___len__();
    }

    final int list___len__() {
        return size();
    }

    protected PyObject getslice(int start, int stop, int step) {
        if (step > 0 && stop < start)
            stop = start;
        int n = sliceLength(start, stop, step);
        PyObject[] newList = new PyObject[n];
        PyObject[] array = getArray();

        if (step == 1) {
            System.arraycopy(array, start, newList, 0, stop-start);
            return new PyList(newList);
        }
        int j = 0;
        for (int i=start; j<n; i+=step) {
            newList[j] = array[i];
            j++;
        }
        return new PyList(newList);
    }

    protected void del(int i) {
        remove(i);
    }

    protected void delRange(int start, int stop, int step) {
        if (step == 1) {
            remove(start, stop);
        }
        else if (step > 1) {
            for(int i=start;i<stop;i+=step) {
                remove(i);
                i--;
                stop--;
            }
        }
        else if (step < 0) {
            for(int i=start;i>=0&&i>=stop;i+=step) {
                remove(i);
            }
        }
    }

    protected void set(int i, PyObject value) {
        list.pyset(i, value);
    }

//    protected void setslice(int start, int stop, int step, PyObject value) {
//
//        if (step != 1)
//            throw Py.ValueError("step size must be 1 for setting list slice");
//        if (stop < start)
//            stop = start;
//        
//        if (value instanceof PySequenceList) {
//            
//            if (value instanceof PyList) {
//                PyObject[] otherArray = null;
//                PyObject[] array = getArray(); 
//                   PySequenceList seqList = (PySequenceList)value;
//                   otherArray = seqList.getArray();
//                   if (otherArray == array) {
//                       otherArray = (PyObject[])otherArray.clone();
//                   }
//                   list.replaceSubArray(start, stop, otherArray, 0, seqList.size());
//            } else {
//                   throw Py.TypeError("can only concatenate list (not \"" +
//                           value.getType() + "\") to list");
//            }
//        } else { 
//            
//            // also allow java.util.List
//            List other = (List)value.__tojava__(List.class);
//            if(other != Py.NoConversion) {
//                   int n = other.size();
//                   list.ensureCapacity(start + n);
//                   for(int i=0; i<n; i++) {
//                       list.add(i+start, other.get(i));
//                   }
//            } else {        
//                throw Py.TypeError(
//                              "rhs of setslice must be a sequence or java.util.List");
//            }
//        }
//    }    
    protected void setslice(int start, int stop, int step, PyObject value) {

        if (stop < start)
            stop = start;
        
        if (step == 1) {
            if (value instanceof PySequence) {
            
                PySequence seq = (PySequence)value;

                PyObject[] otherArray = null;
                PyObject[] array = getArray(); 

                if (value instanceof PySequenceList) {
                    PySequenceList seqList = (PySequenceList)value;
                    otherArray = seqList.getArray();
                    if (otherArray == array) {
                        otherArray = (PyObject[])otherArray.clone();
                    }
                    list.replaceSubArray(start, stop, otherArray, 0, seqList.size());
                } else {
                    int n = seq.__len__();
                    list.ensureCapacity(start + n);
                    for(int i=0; i<n; i++) {
                        list.add(i+start, seq.pyget(i));
                    }
                }
            } else if (value instanceof List) { 
                    List other = (List)value.__tojava__(List.class);
                    if(other != Py.NoConversion && other != null) {
                        int n = other.size();
                        list.ensureCapacity(start + n);
                        for(int i=0; i<n; i++) {
                            list.add(i+start, other.get(i));
                        }
                    }
            } else {        
                throw Py.TypeError(
                                "rhs of setslice must be a sequence or java.util.List");
            }
        } else if (step > 1){
            if (value instanceof PySequence) {
                PySequence seq = (PySequence)value;
                int n = seq.__len__();
                for(int i=0,j=0; i<n; i++,j+=step) {
                    list.pyset(j+start, seq.pyget(i));
                }
            } else {
                throw Py.TypeError(
                             "setslice with java.util.List and step != 1 not supported yet.");
            }
                
        } else if (step < 0) {
            if (value instanceof PySequence) {
                PySequence seq = (PySequence)value;
                int n = seq.__len__();
                if (seq == this) {
                    PyList newseq = new PyList();
                    PyObject iter = seq.__iter__();
                    for (PyObject item = null; (item = iter.__iternext__()) != null; ) {
                        newseq.append(item);
                    }
                    seq = newseq;
                }
                for(int i=0,j=list.size() - 1; i<n; i++,j+=step) {
                    list.pyset(j, seq.pyget(i));
                }
            } else {
                throw Py.TypeError(
                            "setslice with java.util.List and step != 1 not supported yet.");
            }
         }
    }
    
    protected PyObject repeat(int count) {
        int l = size();
        PyObject[] newList = new PyObject[l*count];
        for (int i=0; i<count; i++) {
            System.arraycopy(getArray(), 0, newList, i*l, l);
        }
        return new PyList(newList);
    }

    public PyObject __imul__(PyObject o) {
        return list___imul__(o);
    }

    final PyObject list___imul__(PyObject o) {
        if (!(o instanceof PyInteger || o instanceof PyLong))
            throw Py.TypeError("can't multiply sequence to non-int");
        int l = size();
        int count = ((PyInteger)o.__int__()).getValue();

        int newSize = l * count;
        list.ensureCapacity(newSize);
        list.setSize(newSize);
        //resize(l * count);
        
        PyObject[] array = getArray();
        for (int i=1; i<count; i++) {
            System.arraycopy(array, 0, array, i*l, l);
        }
        return this;
    }

    final PyObject list___mul__(PyObject o) {
        if (!(o instanceof PyInteger || o instanceof PyLong))
            throw Py.TypeError("can't multiply sequence to non-int");
        int count = ((PyInteger)o.__int__()).getValue();
        return repeat(count);
    }

    final PyObject list___rmul__(PyObject o) {
        if (!(o instanceof PyInteger || o instanceof PyLong))
            throw Py.TypeError("can't multiply sequence to non-int");
        int count = ((PyInteger)o.__int__()).getValue();
        return repeat(count);
    }

    public PyObject __add__(PyObject o) {
        return list___add__(o);
    }

    final PyObject list___add__(PyObject o) {
        PyList sum = null;
        if (o instanceof PyList) {
            PyList other = (PyList)o;           
            int thisLen = size();
            int otherLen = other.size();
            PyObject[] newList = new PyObject[thisLen+otherLen];
            System.arraycopy(getArray(), 0, newList, 0, thisLen);
            System.arraycopy(other.getArray(), 0, newList, thisLen, otherLen);
            sum = new PyList(newList);
        } else if ( !(o instanceof PySequenceList) ) {
            // also support adding java lists (but not PyTuple!)
            Object oList = o.__tojava__(List.class);
            if (oList != Py.NoConversion && oList != null) {
                List otherList = (List)oList;
                sum = new PyList();
                sum.list_extend(this);
                for (Iterator i = otherList.iterator(); i.hasNext(); ) {
                    sum.add(i.next());
                }
            }
        }
        return sum;
    }
 
    public PyObject __radd__(PyObject o) {
        return list___radd__(o);
    }

    final PyObject list___radd__(PyObject o) {
        // Support adding java.util.List, but prevent adding PyTuple.
        // 'o' should never be a PyList since __add__ is defined.
        PyList sum = null;
        if (o instanceof PySequence) {
            return null;
        }
        Object oList = o.__tojava__(List.class);
        if(oList != Py.NoConversion && oList != null) {
            sum = new PyList();
            sum.addAll((List)oList);
            sum.extend(this);
        }
        return sum;
    }
    
    protected String unsupportedopMessage(String op, PyObject o2) {
        if (op.equals("+")) {
            return "can only concatenate list (not \"{2}\") to list";
        }
        return null;
    }

    protected String runsupportedopMessage(String op, PyObject o2) {
        if (op.equals("+")) {
            return "can only concatenate list (not \"{1}\") to list";
        }
        return null;
    }
    
    public String toString() {
        return list_toString();
    }

    final String list_toString() {
        ThreadState ts = Py.getThreadState();
        if (!ts.enterRepr(this)) { 
            return "[...]";
        }

        StringBuffer buf = new StringBuffer("[");
        int length = size();
        PyObject[] array = getArray();
        
        for (int i=0; i<length-1; i++) {
            buf.append((array[i]).__repr__().toString());
            buf.append(", ");
        }
        if (length > 0)
            buf.append((array[length-1]).__repr__().toString());
        buf.append("]");

        ts.exitRepr(this);
        return buf.toString();
    }

    /**
     * Add a single element to the end of list.
     *
     * @param o the element to add.
     */
    public void append(PyObject o) {
        list_append(o);
    }

    final void list_append(PyObject o) {
        pyadd(o);
    }

    /**
     * Return the number elements in the list that equals the argument.
     *
     * @param o the argument to test for. Testing is done with
     *          the <code>==</code> operator.
     */
    public int count(PyObject o) {
        return list_count(o);
    }

    final int list_count(PyObject o) {
        int count = 0;
        PyObject[] array = getArray();
        for (int i=0, n = size(); i<n; i++) {
            if (array[i].equals(o))
                count++;
        }
        return count;
    }

    /**
     * return smallest index where an element in the list equals
     * the argument.
     *
     * @param o the argument to test for. Testing is done with
     *          the <code>==</code> operator.
     */
    public int index(PyObject o) {
        return list_index(o, 0, size());
    }
    
    public int index(PyObject o, int start) {
        return list_index(o, start, size());
    }
    
    // CAU: not referenced anywheir, why is this here?
    public int index(PyObject o, int start, int stop) {
        return list_index(o, start, stop);
    }

    final int list_index(PyObject o, int start, int stop) {
        return _index(o, "list.index(x): x not in list", start, stop);
    }
    
    final int list_index(PyObject o, int start) {
        return _index(o, "list.index(x): x not in list", start, size());
    }
    
    final int list_index(PyObject o) {
        return _index(o, "list.index(x): x not in list", 0, size());
    }

    private int _index(PyObject o, String message, int start, int stop) {
        
        //Follow Python 2.3+ behavior
        int validStop = calculateIndex(stop);
        int validStart = calculateIndex(start);
        
        PyObject[] array = getArray();
        int i=validStart;
        for (; i<validStop; i++) {
            if (array[i].equals(o))
                break;
        }
        if (i == validStop)
            throw Py.ValueError(message);
        return i;
    }    

    //This is closely related to fixindex in PySequence, but less strict
    //fixindex returns -1 if index += length < 0 or if index >= length
    //where this function returns 0 in former case and length in the latter.
    //I think both are needed in different cases, but if this method turns
    //out to be needed in other sequence subclasses, it should be moved to
    //PySequence.
    private int calculateIndex(int index) {
        int length = size();
        if (index < 0) {
            index = index += length;
            if (index < 0) {
                index = 0;
            }
        } else if (index > length) {
            index = length;
        }
        return index;
    }


    /**
     * Insert the argument element into the list at the specified
     * index.
     * <br>
     * Same as <code>s[index:index] = [o] if index &gt;= 0</code>.
     *
     * @param index the position where the element will be inserted.
     * @param o     the element to insert.
     */
    public void insert(int index, PyObject o) {
        list_insert(index, o);
    }

    final void list_insert(int index, PyObject o) {
        if (index < 0) index = Math.max(0, size() + index);
        if (index > size()) index = size();
        list.pyadd(index, o);
    }

    /**
     * Remove the first occurence of the argument from the list.
     * The elements arecompared with the <code>==</code> operator.
     * <br>
     * Same as <code>del s[s.index(x)]</code>
     *
     * @param o the element to search for and remove.
     */
    public void remove(PyObject o) {
        list_remove(o);
    }

    final void list_remove(PyObject o) {
        del(_index(o, "list.remove(x): x not in list", 0, size()));
    }

    /**
     * Reverses the items of s in place.
     * The reverse() methods modify the list in place for economy
     * of space when reversing a large list. It doesn't return the
     * reversed list to remind you of this side effect.
     */
    public void reverse() {
        list_reverse();
    }

    final void list_reverse() {
        PyObject tmp;
        int n = size();
        PyObject[] array = getArray();
        int j = n-1;
        for (int i=0; i<n/2; i++, j--) {
            tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }
    }

    /**
     * Removes and return the last element in the list.
     */
    public PyObject pop() {
        return list_pop();
    }

    final PyObject list_pop() {
        return pop(-1);
    }

    /**
     * Removes and return the <code>n</code> indexed element in the
     * list.
     *
     * @param n the index of the element to remove and return.
     */
    public PyObject pop(int n) {
        return list_pop(n);
    }

    final PyObject list_pop(int n) {
        int length = size();
        if (length==0) {
            throw Py.IndexError("pop from empty list");
        }
        if (n < 0)
            n += length;
        if (n < 0 || n >= length)
            throw Py.IndexError("pop index out of range");
        PyObject v = pyget(n);

        setslice(n, n+1, 1, Py.EmptyTuple);
        return v;
    }

    /**
     * Append the elements in the argument sequence to the end of the list.
     * <br>
     * Same as <code>s[len(s):len(s)] = o</code>.
     *
     * @param o the sequence of items to append to the list.
     */
    public void extend(PyObject o) {
        list_extend(o);
    }

    final void list_extend(PyObject o) {
        int length = size();
        setslice(length, length, 1, o);
    }

    public PyObject __iadd__(PyObject o) {
        return list___iadd__(o);
    }

    final PyObject list___iadd__(PyObject o) {
        extend(fastSequence(o, "argument to += must be a sequence"));
        return this;
    }

    /**
     * Sort the items of the list in place. The compare argument is a
     * function of two arguments (list items) which should return
     * -1, 0 or 1 depending on whether the first argument is
     * considered smaller than, equal to, or larger than the second
     * argument. Note that this slows the sorting process down
     * considerably; e.g. to sort a list in reverse order it is much
     * faster to use calls to the methods sort() and reverse() than
     * to use the built-in function sort() with a comparison function
     * that reverses the ordering of the elements.
     *
     * @param compare the comparison function.
     */
    public synchronized void sort(PyObject compare) {
        list_sort(compare);
    }

    final synchronized void list_sort(PyObject compare) {
        MergeState ms = new MergeState(getArray(), size(), compare);
        ms.sort();
    }

    /**
     * Sort the items of the list in place. Items is compared with the
     * normal relative comparison operators.
     */
    public void sort() {
        list_sort();
    }

    final void list_sort() {
        list_sort(null);
    }

    public int hashCode() {
        return list_hashCode();
    }

    final int list_hashCode() {
        throw Py.TypeError("unhashable type");
    }
}
