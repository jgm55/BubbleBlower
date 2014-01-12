package com.me.bubbles;

import com.badlogic.gdx.math.Vector2;

public class Bubble {
	
	private Vector2 velocity = new Vector2();
	private Vector2 position = new Vector2();
	private float radius;
	
	public Bubble(Vector2 startPosition, Vector2 startVelocity, float radius){
		this.velocity = startVelocity;
		this.position = startPosition;
		this.radius = radius;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		result = (int) (prime * result + radius);
		result = prime * result
				+ ((velocity == null) ? 0 : velocity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bubble other = (Bubble) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (radius != other.radius)
			return false;
		if (velocity == null) {
			if (other.velocity != null)
				return false;
		} else if (!velocity.equals(other.velocity))
			return false;
		return true;
	}

	public Vector2 getPosition() {
		position = position.add(velocity);
		velocity.scl(.99f);
		if(velocity.y >= -.1f){
			velocity.y = -.1f;
		}
		return position;
	}
	public Vector2 getVelocity(){
		return velocity;
	}
	public float getRadius(){
		return radius;
	}
}
