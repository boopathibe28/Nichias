package com.nichias.utils;

import com.google.android.libraries.places.api.model.Place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlacesFieldSelector {

    private final List<PlaceField> placeFields;

    public PlacesFieldSelector() {
        this(Arrays.asList(Place.Field.values()));
    }

    public PlacesFieldSelector(List<Place.Field> validFields) {
        placeFields = new ArrayList<>();
        for (Place.Field field : validFields) {
            placeFields.add(new PlaceField(field));
        }
    }

    /**
     * Returns all {@link Place.Field} that are selectable.
     */
    public List<Place.Field> getAllFields() {
        List<Place.Field> list = new ArrayList<>();
        for (PlaceField placeField : placeFields) {
            list.add(placeField.field);
        }

        return list;
    }


    private static class PlaceField {
        final Place.Field field;
        boolean checked;

        public PlaceField(Place.Field field) {
            this.field = field;
        }
    }

} 