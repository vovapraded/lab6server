package org.example.commands;

import org.junit.jupiter.api.Test;

class FilterLessThanVenueTest {

    @Test
    void execute() {
        FilterLessThanVenue filterLessThanVenue = new FilterLessThanVenue();
        filterLessThanVenue.setStringArg("3"
        );
        filterLessThanVenue.execute();
    }
}