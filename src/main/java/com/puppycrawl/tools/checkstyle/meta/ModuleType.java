package com.puppycrawl.tools.checkstyle.meta;

/** Enum holding the types of module which can exist. */
public enum ModuleType {

    /** Module is a Checkstyle check. */
    CHECK("check"),

    /** Module is a Checkstyle filter. */
    FILTER("filter"),

    /** Module is a Checkstyle file filter. */
    FILEFILTER("file-filter");

    /** String representation of the module type. */
    private final String label;

    /**
     * Creates a new instance.
     *
     * @param label label of module
     */
    ModuleType(String label) {
        this.label = label;
    }

    /**
     * Get label corresponding to a module type.
     *
     * @return module type label
     */
    public String getLabel() {
        return label;
    }
}
