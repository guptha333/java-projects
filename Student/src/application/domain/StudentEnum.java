package application.domain;

public class StudentEnum {
	public enum BloodGroupsListEnum {
		APOS {
			@Override
			public String toString() {
				return "A+";
			}
		},
		ANEG {
			@Override
			public String toString() {
				return "A-";
			}
		},
		BPOS {
			@Override
			public String toString() {
				return "B+";
			}
		},
		BNEG {
			@Override
			public String toString() {
				return "B-";
			}
		},
		ABPOS {
			@Override
			public String toString() {
				return "AB+";
			}
		},
		ABNEG {
			@Override
			public String toString() {
				return "AB-";
			}
		},
		OPOS {
			@Override
			public String toString() {
				return "O+";
			}
		},
		ONEG {
			@Override
			public String toString() {
				return "O-";
			}
		}
	}
}
