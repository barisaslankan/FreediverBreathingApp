package com.barisaslankan.freedivingbreathing.domain.model

import com.barisaslankan.freedivingbreathing.util.toRealmInstant
import io.realm.kotlin.types.RealmInstant
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId
import java.time.Instant

class BreatheRecord: RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var note : String = ""
    var duration : Long = 0L
    var date: RealmInstant = Instant.now().toRealmInstant()
}

