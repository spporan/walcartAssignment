package poran.cse.walcartassignment.presentation.utils

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle

interface OnBackPressedDelegation {
    fun registerCallbacks(
        fragmentActivity: FragmentActivity?,
        lifecycle: Lifecycle,
        onBackPressed: () -> Unit
    )
}