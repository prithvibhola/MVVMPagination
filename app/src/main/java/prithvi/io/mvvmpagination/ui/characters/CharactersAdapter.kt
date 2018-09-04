package prithvi.io.mvvmpagination.ui.characters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.holder_character.view.*
import prithvi.io.mvvmpagination.R
import prithvi.io.mvvmpagination.data.models.Character
import prithvi.io.mvvmpagination.ui.common.PaginatedAdapter
import prithvi.io.mvvmpagination.utility.extentions.inflate

class CharactersAdapter(context: Context, retryCallback: () -> Unit) : PaginatedAdapter<Character>(
        context,
        R.string.loading,
        R.string.error_loading,
        retryCallback
) {

    override fun onCreateItemViewHolder(parent: ViewGroup) = CharacterViewHolder(parent.inflate(R.layout.holder_character))

    inner class CharacterViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(item: Character) {
            itemView.apply {
                tvName.text = item.name
                Glide.with(context)
                        .load(item.thumbnail)
                        .into(ivThumbnail)
            }
        }
    }
}