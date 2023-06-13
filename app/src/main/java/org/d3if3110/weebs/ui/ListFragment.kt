package org.d3if3110.weebs.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.d3if3110.weebs.MainActivity
import org.d3if3110.weebs.MainAdapter
import org.d3if3110.weebs.R
import org.d3if3110.weebs.data.SettingDataStore
import org.d3if3110.weebs.data.dataStore
import org.d3if3110.weebs.databinding.FragmentMainBinding
import org.d3if3110.weebs.network.ApiStatus


class ListFragment : Fragment() {
    private val layoutDataStore: SettingDataStore by lazy {
        SettingDataStore(requireContext().dataStore)
    }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private lateinit var myAdapter: MainAdapter
    private lateinit var binding: FragmentMainBinding
    private var isLinearLayout = true
    companion object {
        const val EXTRA_MESSAGE = "com.example.myapp.MESSAGE"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        myAdapter = MainAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }
        viewModel.getStatus().observe(viewLifecycleOwner) { updateProgress(it)
        }
        viewModel.scheduleUpdater(requireActivity().application)
        //val messageTextView = findViewById<TextView>(R.id.messageTextView)
        //val message = context.getStringExtra(EXTRA_MESSAGE)
        //messageTextView.text = message
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            //adapter = MainAdapter(getData())
            setHasFixedSize(true)
        }
        layoutDataStore.preferenceFlow.asLiveData().observe(viewLifecycleOwner) {
            isLinearLayout = it
            setLayout()
            activity?.invalidateOptionsMenu()
        }
    }

    private fun updateProgress(status: ApiStatus) { when (status) {
        ApiStatus.LOADING -> {
            binding.progressBar.visibility = View.VISIBLE
        }
        ApiStatus.SUCCESS -> {
            binding.progressBar.visibility = View.GONE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestNotificationPermission()
            }
        }
        ApiStatus.FAILED -> {
            binding.progressBar.visibility = View.GONE
            binding.networkError.visibility = View.VISIBLE }
    } }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
        inflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu.findItem(R.id.action_switch_layout)
        setIcon(menuItem)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_switch_layout) {
            lifecycleScope.launch {
                layoutDataStore.saveLayout(!isLinearLayout, requireContext())
            }
            return true
        }
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                R.id.action_listFragment_to_historiFragment
                )
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_listFragment_to_aboutFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item) }

    private fun setLayout() {
        binding.recyclerView.layoutManager = if (isLinearLayout)
            LinearLayoutManager(context)
        else
            GridLayoutManager(context, 2)
    }

    private fun setIcon(menuItem: MenuItem) {
        val iconId = if (isLinearLayout)
        R.drawable.ic_baseline_grid_view_24
    else
        R.drawable.ic_baseline_view_list_24
        menuItem.icon = ContextCompat.getDrawable(requireContext(), iconId)
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission( requireContext(),
                Manifest.permission.POST_NOTIFICATIONS ) !=
            PackageManager.PERMISSION_GRANTED
        ){ ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            MainActivity.PERMISSION_REQUEST_CODE
        ) }
    }



//    private fun getData(): List<Komik> { return listOf(
//        Komik("Reincarnated to Slime","転生したらスライムだった件",
//            "Fuze", "Action, Adventure, Fantasy", 2012, R.drawable.satu),
//        Komik("Love is War","かぐや様は告らせたい",
//            "Akasaka Aka", "Comedy, Drama, Romance", 2013, R.drawable.dua),
//        Komik("Bleach","ブリーチ", "Kubo Tite",
//            "Action, Adventure, Shonen", 2006, R.drawable.empat),
//        Komik("Tomie","富江", "Junji Ito",
//            "Pyschological Horror, Supernatural", 2014, R.drawable.tiga),
//    )
//    }

}

