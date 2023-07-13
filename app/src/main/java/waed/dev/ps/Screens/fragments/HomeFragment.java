package waed.dev.ps.Screens.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import waed.dev.ps.Adapters.MainNewsAdapter;
import waed.dev.ps.Models.News;
import waed.dev.ps.R;
import waed.dev.ps.Screens.activities.BooksActivity;
import waed.dev.ps.Screens.activities.CardsActivity;
import waed.dev.ps.Screens.activities.DesignsActivity;
import waed.dev.ps.Screens.activities.StatisticsActivity;
import waed.dev.ps.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        new Handler().postDelayed(() -> binding.importantNews.setSelected(true), 1500);

        setupListener();

        var models = fetchNewsData();

        setupNewsAdapter(models);
    }

    @NonNull
    private static ArrayList<News> fetchNewsData() {
        ArrayList<News> models = new ArrayList<>();
        models.add(new News("dfhgdhdh7dfh0", R.drawable.news_example_image, "ماذا حدث في الـ17 من نيسان ؟", "يوم الأسير الفلسطيني هو يوم تضامني مع الأسرى الفلسطينيين في السجون والمعتقلات الإسرائيلية، ويوافق 17 نيسان/أبريل من كُل عام.\\n\\nفي عام 1974 أقر المجلس الوطني الفلسطيني باعتباره السلطة العليا لمنظمة التحرير الفلسطينية، خلال دورته العادية يوم السابع عشر من نيسان/أبريل، يومًا وطنيًا للوفاء للأسرى الفلسطينيين وتضحياتهم، باعتباره يوماً لشحذ الهمم وتوحيد الجهود، لنصرتهم ومساندتهم ودعم حقهم بالحرية، ولتكريمهم وللوقوف بجانبهم وبجانب ذويهم، وأيضاً بهدف إثبات الوفاء لشهداء الحركة الأسيرة.ومنذ ذلك التاريخ حتى اليوم يتم إحياء هذا اليوم من كل عام، حيثُ يحيه الشعب الفلسطيني في فلسطين والشتات سنويًا بوسائل وأشكال متعددة."));
        models.add(new News("dfhgdhdh7dfh0", R.drawable.img3, "يوم الأرض.", "يوم الأرض الفلسطيني هو يوم يُحييه الفلسطينيون في 30 آذار من كلِ سنة، وتَعود أحداثه لآذار 1976 بعد أن قامت السّلطات الصهيونية بمصادرة آلاف الدّونمات من الأراضي ذات الملكيّة الخاصّة أو المشاع في نطاق حدود مناطق ذات أغلبيّة سكانيّة فلسطينيّة، وقد عم اضراب عام ومسيرات من الجليل إلى النقب وأندلعت مواجهات أسفرت عن سقوط ستة فلسطينيين وأُصيب واعتقل المئات. ويعتبر يوم الأرض حدثاً محورياً في الصراع على الأرض وفي علاقة المواطنين العرب بالجسم السياسي الصهيوني بحيث أن هذه هي المرة الأولى التي يُنظم فيها العرب في فلسطين منذ عام 1948 احتجاجات منظمة رداً على السياسات الصهيونية بصفة جماعية وطنية فلسطينية"));
        models.add(new News("dfhgdhdh7dfh0", R.drawable.img4, "يوم النكبة.", "ذكرى النكبة (15 مايو) هو يوم إحياء الذكرى السنوية لنكبة الشعب الفلسطيني، حيث يتذكر الفلسطينيون ما حل بهم من مأساة إنسانية وتهجير. اتُّفِق على أن يكون يوم الذكرى هو اليوم التالي لذكرى إعلان قيام دولة إسرائيل وذلك في إشارة إلى أن كل ما قامت به المجموعات المسلحة الصهيونية في حق الشعب الفلسطيني كان من أجل التمهيد لقيام هذه الدولة التي أريد منها أن تكون دولة لليهود فقط."));
        models.add(new News("dfhgdhdh7dfh0", R.drawable.img5, "يوم الإستقلال الفلسطيني.", "يوم الاستقلال الإسرائيلي، (بالعبرية: יום העצמאות، يوم هعتسماؤوت). هو يوم وطني يحتفل به الإسرائيليون، في اليوم الخامس من شهر أيار حسب التقويم العبري. في ذكرى إعلان قيام دولة إسرائيل، واعتماد وثيقة الاستقلال، ونهاية الانتداب البريطاني على فلسطين، في 14 مايو 1948."));
        models.add(new News("dfhgdhdh7dfh0", R.drawable.img6, "يوم العمال العالمي.", "كانت بداية عيد العمال يوم 21 ابريل/نيسان 1856 في أستراليا، ثم انتقلت إلى الولايات المتحدة الأميركية، حيث طالب العمال في ولاية شيكاغو عام 1886 بتخفيض ساعات العمل اليومي إلى ثماني ساعات، وتكرر الطلب في ولاية كاليفورنيا. وفي تورونتو الكندية حضر زعيم العمال الأميركي بيتر ماكغواير احتفالا بعيد العمال، فنقل الفكرة وتم أول عيد للعمال في الولايات المتحدة الأمريكية تم الاحتفال به في 5 سبتمبر 1882 في مدينة نيويورك. أثمر نضال العمال في كندا قانون الاتحاد التجاري الذي أعطى الصفة القانونية للعمال ووفر الحماية لنشاط الاتحاد عام 1872."));
        return models;
    }

    private void setupNewsAdapter(ArrayList<News> models) {
        MainNewsAdapter adapter = new MainNewsAdapter(models);
        binding.newsRecyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        binding.newsRecyclerView.setLayoutManager(manager);
        binding.newsRecyclerView.setHasFixedSize(true);
    }

    private void setupListener() {
        binding.statisticsCard.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), StatisticsActivity.class));
        });

        binding.booksCard.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), BooksActivity.class));
//            startActivity(new Intent(getActivity(), PdfActivity.class));
        });

        binding.cardsCard.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), CardsActivity.class));
        });

        binding.postersCard.setOnClickListener(view -> {
            startActivity(new Intent(getActivity(), DesignsActivity.class));
        });
    }
}