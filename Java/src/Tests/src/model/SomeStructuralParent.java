/*-- $Copyright (C) 2012-13 Felix Dobslaw$

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished
to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */package model;

public class SomeStructuralParent {

	private int someSharedPrimitiveSub;
	
	private long anotherSharedPrimitiveSub;
	
	private String someSharedStructuralSub;
	
	public SomeStructuralParent(int someSharedPrimitiveSub) {
		this.someSharedPrimitiveSub = someSharedPrimitiveSub;
	}
	
	public int getSomeSharedPrimitiveSub() {
		return someSharedPrimitiveSub;
	}
	
	public long getAnotherSharedPrimitiveSub() {
		return anotherSharedPrimitiveSub;
	}
	
	public void setSomeSharedStructuralSub(String someSharedStructuralSub) {
		this.someSharedStructuralSub = someSharedStructuralSub;
	}
	
	public String getSomeSharedStructuralSub() {
		return someSharedStructuralSub;
	}
	
	public void setAnotherSharedPrimitiveSub(long anotherSharedPrimitiveSub) {
		this.anotherSharedPrimitiveSub = anotherSharedPrimitiveSub;
	}
}