package com.damiralyukov.ecochelyabinsk.ui

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.damiralyukov.ecochelyabinsk.data.Company
import com.damiralyukov.ecochelyabinsk.data.District
import com.damiralyukov.ecochelyabinsk.ui.fragments.CategoryFragmentDirections

class BindingAdapters {
    companion object {
        @BindingAdapter("app:isEmpty")
        @JvmStatic
        fun isEmpty(view: View, empty: MutableLiveData<Boolean>) {
            when (empty.value) {
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("app:callPhoneNumber")
        @JvmStatic
        fun callPhoneNumber(view: View, phone: String) {
            view.setOnClickListener {
                view.context.startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null)))
            }
        }

        @BindingAdapter("app:openAddressInGoogleMap")
        @JvmStatic
        fun openAddressInGoogleMap(view: View, address: String) {
            view.setOnClickListener {
                val builder: Uri.Builder = Uri.Builder().scheme("geo").path("0,0").appendQueryParameter("q", "г. Челябинск, $address")

                val intent: Intent = Intent(Intent.ACTION_VIEW, builder.build())

                if (intent.resolveActivity(view.context.packageManager) != null) {
                    view.context.startActivity(intent)
                }
            }
        }

        @BindingAdapter("app:navigateToUpdateCompanyFragment")
        @JvmStatic
        fun navigateToUpdateCompanyFragment(view: ImageView, company: Company) {
            view.setOnClickListener {
                view.findNavController().navigate(CategoryFragmentDirections.actionCategoryFragmentToUpdateCompanyFragment(company))
            }
        }

        @BindingAdapter("app:parseDistrictToInt")
        @JvmStatic
        fun parseDistrictToInt(view: Spinner, district: District) {
            when (district) {
                District.CENTRALNIY -> view.setSelection(0)
                District.TOPOLINAYA_ALLEYA -> view.setSelection(1)
                District.SEVERO_ZAPAD -> view.setSelection(2)
                District.SOVETSKIY -> view.setSelection(3)
                District.CHTZ -> view.setSelection(4)
                District.CHMZ -> view.setSelection(5)
                District.LENINSKIY -> view.setSelection(6)
                District.KURCHATOVSKIY -> view.setSelection(7)
            }
        }
    }
}