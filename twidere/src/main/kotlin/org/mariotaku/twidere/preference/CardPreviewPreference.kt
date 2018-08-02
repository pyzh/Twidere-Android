/*
 * 				Twidere - Twitter client for Android
 * 
 *  Copyright (C) 2012-2014 Mariotaku Lee <mariotaku.lee@gmail.com>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mariotaku.twidere.preference

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.databinding.DataBindingUtil
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceViewHolder
import android.util.AttributeSet
import com.bumptech.glide.Glide
import org.mariotaku.twidere.R
import org.mariotaku.twidere.TwidereConstants.SHARED_PREFERENCES_NAME
import org.mariotaku.twidere.adapter.DummyItemAdapter
import org.mariotaku.twidere.databinding.ItemStatusBinding

class CardPreviewPreference(
        context: Context,
        attrs: AttributeSet? = null
) : Preference(context, attrs), OnSharedPreferenceChangeListener {

    private lateinit var binding: ItemStatusBinding
    private val adapter: DummyItemAdapter = DummyItemAdapter(context, requestManager = Glide.with(context))

    init {
        val preferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME,
                Context.MODE_PRIVATE)
        layoutResource = R.layout.layout_preferences_card_preview_compact
        preferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(preferences: SharedPreferences, key: String) {
        adapter.updateOptions()
        notifyChanged()
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        if (!this::binding.isInitialized) {
            val widget = holder.findViewById(R.id.itemContent)
            binding = DataBindingUtil.getBinding(widget) ?: ItemStatusBinding.bind(widget)
        }
        binding.favorite.setOnClickListener {
            //            (holder as StatusViewHolder).playLikeAnimation(LikeAnimationDrawable.OnLikedListener { false })
        }
        super.onBindViewHolder(holder)
    }

}
