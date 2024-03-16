package com.arfian.tmeu.domain.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Sales: RealmObject {
    @PrimaryKey
    var _id : ObjectId = ObjectId()
    var target: Int = 0
    var ach: Int = 0
    var salesNet: Int = 0
    var struk: Int = 0
    var apc: Int = 0
    var varian: Int = 0
    var penggantian: Int = 0
    var spd: Int = 0
    var std: Int = 0
    var apc1: Int = 0
    var margin: Int = 0
    var salesLpptk: Int = 0
    var akmSales: Int = 0
}