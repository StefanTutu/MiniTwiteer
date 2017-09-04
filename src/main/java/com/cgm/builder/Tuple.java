package com.cgm.builder;

public class Tuple<X, Y> {
	public final X x;
	public final Y y;

	public X getX() {
		return x;
	}

	public Y getY() {
		return y;
	}

	public Tuple(X x, Y y) {
		super();
		this.x = x;
		this.y = y;
	}
}