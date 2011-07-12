package de.hypoport.yatwitter.dto;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "likeDo")
public class Like implements IDataTransferObject<Like.Key> {
	
	public static class Key implements Serializable {
		private Integer tweet;
		private String user;

		public Key() {
			super();
		}

		public Key(Integer tweet, String user) {
			super();
			this.tweet = tweet;
			this.user = user;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (tweet == null) {
				if (other.tweet != null)
					return false;
			} else if (!tweet.equals(other.tweet))
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			return true;
		}

		public Integer getTweet() {
			return tweet;
		}

		public String getUser() {
			return user;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((tweet == null) ? 0 : tweet.hashCode());
			result = prime * result + ((user == null) ? 0 : user.hashCode());
			return result;
		}

		public void setTweet(Integer tweet) {
			this.tweet = tweet;
		}

		public void setUser(String user) {
			this.user = user;
		}
	}

	@EmbeddedId
	private Key key;

	public Like() {
		super();
	}

	public Like(Key key) {
		super();
		this.key = key;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Like other = (Like) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public Key getId() {
		return key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	public void setId(Like.Key key) {
		this.key = key;
	}

}
