package com.yudha.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list : ArrayList<Hero> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHero.setHasFixedSize(true)

        list.addAll(ObjectHeroesData.listData)

        showRecyclerList()
    }

    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }

    private fun showRecyclerList() {
        rvHero.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvHero.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallBack(object : ListHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    private fun showRecycleGrid(){
        rvHero.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridHeroAdapter(list)
        rvHero.adapter = gridHeroAdapter

        gridHeroAdapter.setOnItemClickCallback(object : GridHeroAdapter.OnItemClickCallBack{
            override fun onItemClick(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    private fun showRecyclerCardView() {
        rvHero.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list)
        rvHero.adapter = cardViewHeroAdapter

        cardViewHeroAdapter.setOnItemClickCallBack(object : CardViewHeroAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(itemId: Int) {
        when(itemId){
            R.id.action_list ->{
                showRecyclerList()
            }

            R.id.action_grid ->{
                showRecycleGrid()
            }

            R.id.action_cardview -> {
                showRecyclerCardView()
            }
        }
    }
}
