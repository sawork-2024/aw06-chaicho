package com.micropos.product.repository;

import com.micropos.product.model.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class JDRepository implements ProductRepository {
    private List<Product> products = null;

    @Override
    public List<Product> allProducts() {
        try {
            if (products == null)
                products = parseJD("Java");
        } catch (IOException e) {
            products = new ArrayList<>();
        }
        return products;
    }

    @Override
    public Product findProduct(String productId) {
        for (Product p : allProducts()) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    // Method to parse cookies from the cookie header
    private static Map<String, String> parseCookies(String cookieHeader) {
        Map<String, String> cookies = new HashMap<>();
        String[] cookiePairs = cookieHeader.split("; ");
        for (String cookiePair : cookiePairs) {
            String[] parts = cookiePair.split("=", 2);
            if (parts.length == 2) {
                cookies.put(parts[0], parts[1]);
            }
        }
        return cookies;
    }

    public static List<Product> parseJD(String keyword) throws IOException {
        String url = "https://search.jd.com/Search?keyword=" + keyword;


        String cookieHeader = "wlfstk_smdl=nyuvqz0gfaiamy7y72mbow281cfkmm49; __jda=181111935.1716293962247966163877.1716293962.1716293962.1716293962.1; __jdb=181111935.7.1716293962247966163877|1.1716293962; __jdc=181111935; __jdv=95931165|direct|-|none|-|1716293962247; __jdu=1716293962247966163877; 3AB9D23F7A4B3CSS=jdd03PBGY7HBZOSSPTDRPNUOASHIXGGSDEKRFK3WLF2ZVBB3GF2VPQFJEHXUEU22NKM3X6Z5GLO67XGTW5CE7AJDU2J3NTMAAAAMPTMLTFKAAAAAACB3K44JQYSQMFYX; _gia_d=1; TrackID=1BhEXNN9NPgusxi7Igf6bTvgmGWIIEMxmkug1FiyPKjSiviI9_eZOSTObQkKJ7uKzSiYJe_g1aGXlm72aX6ixD8BN9MuJnxO3GR1ddjR_pGX5J-uBmIgzdD-TqYK2DjLbRwHxgTj0FQvY4h7FX2G-gA; thor=B48E2DD79911FF5DA7A7CA3E2E5885E926338D89E64C8E899A5E90534B0D7CEE8D6DD2C056E3D13A9F6914A5374439A742006D307907183CEAB8FA2C4F32F0C991524BE7A69DD91889EB0FC0BA64E554A0F9945A1A8305065C4C89677B6E95BAC31D0D074E80814F0D6A83667F534F22CADA2C9F800E8C48D50E73AE9A14D859F9B3E76CCC64FE0B2FE430E96623F584728B4AEF67F3749FD4652810EFC06858; flash=2_TRt_lHRENBYBgBI9UEAQtJtyOgDAsyxTs_83HKI0lLmVrLFZGF5OY4f31O_lxV2F0M3w1reiuO6OySTFFe4iTDKXbMC9sQdmXRW1HCba-zwWFVvgSz4hNOlf0BgLxUHJLiAbNodLgK1KH2j1iMzXXUtv4a2_9dHFA1ToQELWYbM*; pinId=61WohYO1_6859-pctc58-7V9-x-f3wj7; pin=jd_4f7b7fe31c43e; unick=jd_prb35c8wie2t1w; ceshi3.com=000; _tp=GXn69iyvk4bWVRKGewaoZFJKV3hd7rkSWvXKH1InOdM%3D; _pst=jd_4f7b7fe31c43e; ipLoc-djd=12-904-907-50559; xapieid=jdd03PBGY7HBZOSSPTDRPNUOASHIXGGSDEKRFK3WLF2ZVBB3GF2VPQFJEHXUEU22NKM3X6Z5GLO67XGTW5CE7AJDU2J3NTMAAAAMPTMLTFKAAAAAACB3K44JQYSQMFYX; jsavif=1; jsavif=1; qrsc=3; areaId=12; shshshfpa=c6d05616-6b53-af53-f47c-86e3d3e7b88c-1716293993; shshshfpx=c6d05616-6b53-af53-f47c-86e3d3e7b88c-1716293993; avif=1; shshshfpb=BApXcfBoQmOpAJvgGmRRB50a175RoIQlWBlRoPrxo9xJ1MuSqUYC2; rkv=1.0; 3AB9D23F7A4B3C9B=PBGY7HBZOSSPTDRPNUOASHIXGGSDEKRFK3WLF2ZVBB3GF2VPQFJEHXUEU22NKM3X6Z5GLO67XGTW5CE7AJDU2J3NTM; token=006a9e2ef10bd917aa24a812e70f9dd2,3,953496; __tk=f33876af744061e537e806ae13117881,3,953496";

        Map<String, String> cookies = parseCookies(cookieHeader);

        //解析网页
        Document document = Jsoup.connect(url)
                .cookies(cookies)
                .timeout(10000)
                .get();

        //所有js的方法都能用
        Element element = document.getElementById("J_goodsList");
        //获取所有li标签
        Elements elements = element.getElementsByTag("li");
//        System.out.println(element.html());
        List<Product> list = new ArrayList<>();

        //获取元素的内容
        for (Element el : elements
        ) {
            //关于图片特别多的网站，所有图片都是延迟加载的
            String id = el.attr("data-spu");
            String img = "https:".concat(el.getElementsByTag("img").eq(0).attr("data-lazy-img"));
            String price = el.getElementsByAttribute("data-price").text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            if (title.indexOf("，") >= 0)
                title = title.substring(0, title.indexOf("，"));

            Product product = new Product(id, title, parsePrice(price), img);

            list.add(product);
        }
        return list;

    }
    private static double parsePrice(String priceString) {
        if (priceString.isEmpty()) {
            return 0.0;
        }
        // Remove non-numeric characters, e.g., currency symbols, and commas
        String numericString = priceString.replaceAll("[^\\d.]", "");

        // Parse the cleaned string as a double
        return Double.parseDouble(numericString);
    }
}
