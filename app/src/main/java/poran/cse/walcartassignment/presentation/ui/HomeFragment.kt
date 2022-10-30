package poran.cse.walcartassignment.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import poran.cse.walcartassignment.presentation.utils.OnBackPressedDelegation
import poran.cse.walcartassignment.presentation.utils.OnBackPressedDelegationImpl

class HomeFragment: Fragment(), OnBackPressedDelegation by OnBackPressedDelegationImpl() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerCallbacks(activity, this.lifecycle) {
            // handle fragment onBackPress
        }
    }
}