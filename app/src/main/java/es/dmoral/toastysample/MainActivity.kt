package es.dmoral.toastysample

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.Typeface.BOLD_ITALIC
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*

/**
 * This file is part of Toasty.
 *
 *
 * Toasty is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 *
 * Toasty is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 *
 * You should have received a copy of the GNU General Public License
 * along with Toasty.  If not, see <http:></http:>//www.gnu.org/licenses/>.
 */

class MainActivity : AppCompatActivity() {

    private val formattedMessage: CharSequence
        get() {
            val prefix = "Formatted "
            val highlight = "bold italic"
            val suffix = " text"
            val ssb = SpannableStringBuilder(prefix).append(highlight).append(suffix)
            val prefixLen = prefix.length
            ssb.setSpan(StyleSpan(BOLD_ITALIC), prefixLen, prefixLen + highlight.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return ssb
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button_error_toast.setOnClickListener { Toasty.error(this, R.string.error_message, Toasty.LENGTH_SHORT, true).show() }
        button_success_toast.setOnClickListener { Toasty.success(this, R.string.success_message, Toasty.LENGTH_SHORT, true).show() }
        button_info_toast.setOnClickListener { Toasty.info(this, R.string.info_message, Toasty.LENGTH_SHORT, true).show() }
        button_warning_toast.setOnClickListener { Toasty.warning(this, R.string.warning_message, Toasty.LENGTH_SHORT, true).show() }
        button_normal_toast_wo_icon.setOnClickListener { Toasty.normal(this, R.string.normal_message_without_icon).show() }
        button_normal_toast_w_icon.setOnClickListener {
            val icon = resources.getDrawable(R.drawable.ic_pets_white_48dp)
            Toasty.normal(this, R.string.normal_message_with_icon, icon).show()
        }
        button_info_toast_with_formatting.setOnClickListener { Toasty.info(this, formattedMessage).show() }
        button_custom_config.setOnClickListener {
            Toasty.Config.getInstance().setToastTypeface(Typeface.createFromAsset(assets, "PCap Terminal.otf")).apply()
            Toasty.custom(this, R.string.custom_message, resources.getDrawable(R.drawable.laptop512), Color.BLACK, Color.GREEN, Toasty.LENGTH_SHORT, true, true).show()
            Toasty.Config.reset() // Use this if you want to use the configuration above only once
        }
    }

}
