package com.project.livecoinwatch.presentation

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.project.livecoinwatch.R
import com.project.livecoinwatch.databinding.ActivityCoinPrceListBinding
import com.project.livecoinwatch.domain.CryptoEntity
import com.project.livecoinwatch.presentation.adapters.CryptoAdapter
import javax.inject.Inject


class CryptoPriceListActivity : AppCompatActivity() {


    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    private val component by lazy {
        (application as LiveCoinWatch).component
    }

    private val binding by lazy {
        ActivityCoinPrceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        launchWelcomeActivity()

        val adapter = CryptoAdapter(this)
        adapter.onCoinClickListener = object : CryptoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CryptoEntity) {
                if (isOnePaneMode()) {
                    launchDetailActivity(coinPriceInfo.fromSymbol)
                } else {
                    launchDetailFragment(coinPriceInfo.fromSymbol)
                }
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]
        viewModel.cryptoList.observe(this) {
            adapter.submitList(it)
        }

        updateData()

    }

    private fun updateData() {
        binding.updateData?.setOnClickListener {
            viewModel.updateDataVM()
        }
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

    private fun launchDetailActivity(fromSymbol: String) {
        val intent = CryptoDetailActivity.newIntent(
            this@CryptoPriceListActivity,
            fromSymbol
        )
        startActivity(intent)
    }

    private fun launchDetailFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CryptoDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }

    private fun launchWelcomeActivity() {
        val prefs: SharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val firstRun: Boolean = prefs.getBoolean(KEY_FIRST_RUN, true)

        if (firstRun) {
            startActivity(Intent(this, WelcomeActivity::class.java))
        }
    }

    companion object {
        private val PREFS_NAME = "MyPrefsFile"
        private val KEY_FIRST_RUN = "firstRun"
    }
}
