package com.nichias.utils;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

public class MyMigration implements RealmMigration {
  @Override
  public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

     // DynamicRealm exposes an editable schema
     RealmSchema schema = realm.getSchema();

     // Migrate to version 1: Add a new class.
     // Example:
     // public Person extends RealmObject {
     //     private String name;
     //     private int age;
     //     // getters and setters left out for brevity
     // }
     if (oldVersion == 0) {
        schema.create("1")
            .addField("2", String.class)
            .addField("1", int.class);
        oldVersion++;
     }

     // Migrate to version 2: Add a primary key + object references
     // Example:
     // public Person extends RealmObject {
     //     private String name;
     //     @PrimaryKey
     //     private int age;
     //     private Dog favoriteDog;
     //     private RealmList<Dog> dogs;
     //     // getters and setters left out for brevity
     // }
     if (oldVersion == 1) {
        schema.get("1")
            .addField("1", long.class, FieldAttribute.PRIMARY_KEY)
            .addRealmObjectField("1", schema.get("1"))
            .addRealmListField("1", schema.get("1"));
        oldVersion++;
     }
  }
}