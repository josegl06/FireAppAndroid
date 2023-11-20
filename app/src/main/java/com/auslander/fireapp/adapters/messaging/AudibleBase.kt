package com.auslander.fireapp.adapters.messaging

import androidx.lifecycle.LiveData
import com.auslander.fireapp.model.AudibleState

interface AudibleBase {
    var audibleState: LiveData<Map<String, AudibleState>>?
    var audibleInteraction:AudibleInteraction?
}