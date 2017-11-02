/*
 *             Twidere - Twitter client for Android
 *
 *  Copyright (C) 2012-2017 Mariotaku Lee <mariotaku.lee@gmail.com>
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

package org.mariotaku.twidere.fragment.users

import android.content.Context
import android.os.Bundle
import com.bumptech.glide.RequestManager
import org.mariotaku.kpreferences.get
import org.mariotaku.twidere.R
import org.mariotaku.twidere.adapter.ParcelableUsersAdapter
import org.mariotaku.twidere.annotation.LoadMorePosition
import org.mariotaku.twidere.constant.IntentConstants.EXTRA_ACCOUNT_KEY
import org.mariotaku.twidere.constant.IntentConstants.EXTRA_STATUS_ID
import org.mariotaku.twidere.constant.iWantMyStarsBackKey
import org.mariotaku.twidere.extension.linkHandlerTitle
import org.mariotaku.twidere.fragment.ParcelableUsersFragment
import org.mariotaku.twidere.loader.users.AbsRequestUsersLoader
import org.mariotaku.twidere.loader.users.StatusFavoritersLoader
import org.mariotaku.twidere.model.UserKey

class StatusFavoritersListFragment : ParcelableUsersFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        linkHandlerTitle = if (preferences[iWantMyStarsBackKey]) {
            getString(R.string.title_users_favorited_this)
        } else {
            getString(R.string.title_users_liked_this)
        }
    }

    override fun onCreateUsersLoader(context: Context, args: Bundle, fromUser: Boolean):
            AbsRequestUsersLoader {
        val accountKey = args.getParcelable<UserKey>(EXTRA_ACCOUNT_KEY)
        val statusId = args.getString(EXTRA_STATUS_ID)
        return StatusFavoritersLoader(context, accountKey, statusId, adapter.getData(), false)
    }

    override fun onCreateAdapter(context: Context, requestManager: RequestManager): ParcelableUsersAdapter {
        return super.onCreateAdapter(context, requestManager).apply {
            loadMoreSupportedPosition = LoadMorePosition.NONE
        }
    }

}
