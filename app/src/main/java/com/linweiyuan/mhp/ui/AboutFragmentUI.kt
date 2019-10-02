package com.linweiyuan.mhp.ui

import android.annotation.SuppressLint
import android.view.Gravity
import com.linweiyuan.mhp.BuildConfig
import com.linweiyuan.mhp.R
import com.linweiyuan.mhp.fragment.AboutFragment
import org.jetbrains.anko.*

class AboutFragmentUI : AnkoComponent<AboutFragment> {
    @SuppressLint("SetTextI18n")
    override fun createView(ui: AnkoContext<AboutFragment>) = with(ui) {
        verticalLayout {
            imageView {
                imageResource = R.mipmap.ic_launcher
            }.lparams(width = dimen(R.dimen.image_size), height = dimen(R.dimen.image_size)) {
                gravity = Gravity.CENTER_HORIZONTAL
                topMargin = dimen(R.dimen.value_10)
            }

            themedTextView(R.style.QDCommonDescription) {
                text =
                    "${resources.getString(R.string.current_version)} ${BuildConfig.VERSION_NAME}"
            }.lparams(matchParent) {
                margin = dimen(R.dimen.value_5)
                padding = dimen(R.dimen.value_5)
            }

            scrollView {
                verticalLayout {
                    owner.txtAbout = themedTextView(R.style.QDCommonDescription) {
                        text = resources.getString(R.string.about)
                    }.lparams(matchParent) {
                        margin = dimen(R.dimen.value_5)
                        padding = dimen(R.dimen.value_5)
                    }
                }
            }
        }
    }
}
