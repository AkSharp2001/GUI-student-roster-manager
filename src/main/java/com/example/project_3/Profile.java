package com.example.project_3;

/**
 * A class that defines a Profile object by a student's name and major.
 * This class contains methods to compare with other Profile objects and to
 * generate a String representation of this Profile's information.
 *
 * @author Mervin James, Akshar Patel
 */
public class Profile {
    private final String name;
    private final Major major;

    /**
     * Constructs a Profile object by a student's name and major.
     *
     * @param name  the name of the student.
     * @param major the major of study the student is pursuing.
     */
    public Profile(String name, Major major) {
        this.name = name;
        this.major = major;
    }

    /**
     * Determines if this Profile and another object are equivalent.
     * If the object is a Profile, the method compares both Profile's name
     * and major attributes.
     *
     * @param obj the object that this Profile is being compared to.
     * @return true if both objects are Profile objects with equivalent
     * name and major attributes, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Profile) {
            Profile profile = (Profile) obj;
            return (profile.name.equals(this.name) &&
                    profile.major == this.major);
        }
        return false;
    }

    /**
     * Compares two Profile objects to determine alphabetical order.
     *
     * @param profile the Profile object that this Profile object is being
     *                compared to.
     * @return -1 if this Profile object has a name attribute that is
     * alphabetically preceding the name attribute of the other Profile
     * object, 1 if this Profile object has a name attribute that is
     * alphabetically follows the name attribute of the other Profile
     * object, and 0 if both Profiles' name attributes are alphabetically
     * equivalent.
     */
    public int compareTo(Profile profile) {
        if (this.name.equals(profile.name)) {
            return 0;
        } else if (this.name.compareTo(profile.name) < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    /**
     * Generates a String representation of this Profile object information.
     *
     * @return the String representation of this Profile object's information.
     */
    @Override
    public String toString() {
        return name + ":" + major;
    }
}
