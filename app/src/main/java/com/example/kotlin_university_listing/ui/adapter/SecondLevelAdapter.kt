package com.example.kotlin_university_listing.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.kotlin_university_listing.R

class SecondLevelAdapter(private val context: Context, private val headers: Array<String>, private val data: List<Array<String>>) : BaseExpandableListAdapter()  {
    override fun getGroupCount(): Int {
        return headers.size
    }

    override fun getChildrenCount(p0: Int): Int {
        var children: Array<String>
        children = data.get(p0)
        return children.size
    }

    override fun getGroup(p0: Int): Any {
        return headers[p0]
    }

    override fun getChild(p0: Int, p1: Int): Any {
        var childData: Array<String>
        childData = data.get(p0)
        return childData[p1]
    }

    override fun getGroupId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getChildId(p0: Int, p1: Int): Long {
        return p1.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupView(p0: Int, p1: Boolean, p2: View?, p3: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = p2 ?: inflater.inflate(R.layout.row_second, null)
        val text = view.findViewById<TextView>(R.id.rowSecondText)
        val favoriteButton = view.findViewById<ImageView>(R.id.favorite_button)
        var checkFavorite: Boolean = false

        favoriteButton.setOnClickListener {
            val currentSrc = favoriteButton.drawable

            // Mevcut src'yi kontrol ederek favori butonunun durumunu değiştirin
            if (currentSrc == ContextCompat.getDrawable(context, R.drawable.empty_hearth)) {
                // Eğer boş kalp görünüyorsa dolu kalp olarak değiştirin
                favoriteButton.setImageResource(R.drawable.filled_hearth)
            } else {
                // Eğer dolu kalp görünüyorsa boş kalp olarak değiştirin
                favoriteButton.setImageResource(R.drawable.empty_hearth)
            }
            // Burada favori butonuna tıklandığında yapılacak işlemi tanımlayabilirsiniz
            // Örneğin, bir bildirim gösterebilir, favorilere ekleyebilir veya çıkarabilirsiniz
            // İlgili özelliklere erişip işlem yapabilirsiniz.
            // Örneğin, p0 ve p1 değerlerini kullanarak verilere erişebilirsiniz.
        }


        val groupText = getGroup(p0).toString()
        text.text = groupText
        return view
    }

    override fun getChildView(p0: Int, p1: Int, p2: Boolean, p3: View?, p4: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = p3 ?: inflater.inflate(R.layout.row_third, null)

        val textView = view.findViewById<TextView>(R.id.rowThirdText)

        val childArray = data[p0]
        val text = childArray[p1]

        textView.text = text

        return view
    }

    override fun isChildSelectable(p0: Int, p1: Int): Boolean {
        return true
    }
}