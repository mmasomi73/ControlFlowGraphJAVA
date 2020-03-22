
پیش از توضیحات و مستندات فاز سوم پروژه بستر اجرای و تست پروژه حاضر نام برده می‌شود.  
  
| Windows 10 pro | Os |  
| --- | --- |  
| v13.0.1 | Java |  
| V4.7.2 | ANTLR |  
  
  <p dir='rtl' align='right'>
کدها به دلیل سیستم بلاکینگ ایمیل در ریپازیتوری زیر کامیت گردیده است.  
    </p>
  
https://github.com/mmasomi73/ControlFlowGraphJAVA  
  
<p dir='rtl' align='right'>
برای این فاز از چندین پکیج آماده جهت ذخیره سازی و ترسیم گرافیکی گراف کنترلی استفاده گردیده که از قرار زیر می‌باشد.  
  </p>
  

    commons-lang3-3.8.1.jar  
    commons-text-1.5.jar  
    fastutil-8.2.2.jar  
    guava-26.0-jre.jar  
    jetty-6.1.1.jar  
    jetty-util-6.1.1.jar  
    jgrapht-bundle-1.3.0.jar  
    jgrapht-core-1.3.0.jar  
    jgrapht-demo-1.3.0.jar  
    jgrapht-ext-1.3.0.jar  
    jgrapht-guava-1.3.0.jar  
    jgrapht-io-1.3.0.jar  
    jgrapht-opt-1.3.0.jar  
    jgraphx-3.4.1.3.jar  
    jheaps-0.9.jar  
    junit-4.7.jar  
    mxPdf.jar  
    servlet-api-2.5-6.1.1.jar

  
<p dir='rtl' align='right'>
در این فاز از پروژه الگوریتم Reaching Definitions پیاده سازی شده است. این الگوریتم در پکیج و زیر پوشه زیر قرار دارد. این کلاس دارای دو کلاس کمکی جهت پیاده سازی با نام‌های BitVector و BitVectors می‌باشد که عملکرد bit vector را شبیه سازی می‌کنند. در این الگوریتم برای هر بلاک مجموعه‌های IN, OUT, KILL و GEN اضافه گردیده است.  
  </p>
  
| ~\src\intermediate\optimizer\ReachingDefs |  
| --- |  
  
در زیر تصویر فرم اجرایی و نمونه خروجی برای یک ورودی نمایش داده‌ می‎شود. که هر بخش در تصویر علامت گذاری و در ادامه تشریح می‌گردد.  
  
  <p dir='rtl' align='right'>
بخش 1: در این بخش لیست کلیه بلاک‌ها به ترتیب اجرایی در الگوریتم از بالا به پایین نمایش داده شده است که با انتخاب هر یک اطلاعات آن بلاک در پنل‌های سمت راست نمایش داده می‌شود. این بخش را می‌توان بهبود بخشید، به این صورت که ترتیب انتخاب بلاک‌ها به صورت معکوس DF-order پیمایش گراف جریان باشد. ولی در این جا جهت نمایش خاتمه پذیری و رسیدن به نقطه Fix-Point همیشه اولین بلاک آخرین بلاک جریان کنترلی انتخاب شده.  
 </p>
  <p dir='rtl' align='right'>
**بخش 2:**
 در این بخش مجموعه IN, OUT, KILL و GEN بلاک انتخاب شده در حالت نقطه ثابت نمایش داده شده است.  
    </p>
  <p dir='rtl' align='right'>
**بخش 3:** در این بخش مجموعه IN همه Predecessor-های بلاک انتخاب شده نمایش داده شده است، ذکر این نکته لازم است که همه مقادیر نشان داده شده مقادیر بعد از رسیدن به نقطه ثابت هستند.  
</p>
  <p dir='rtl' align='right'>
**بخش 4:** در این بخش مجموعه OUT همه Successor-های بلاک انتخاب شده نمایش داده شده است، ذکر این نکته لازم است که همه مقادیر نشان داده شده مقادیر بعد از رسیدن به نقطه ثابت هستند.  
  </p>
  <p dir='rtl' align='right'>
**بخش 5:** در این بخش کد سه آدرسه بلاک انتخاب شده نمایش داده شده است.  
  </p>
  <p dir='rtl' align='right'>
**بخش6:** این بخش به عنوان راهنمای Bit Vector می‌باشد و نشان میدهد هر شماره بیت از وکتور مورد نظر مرتبط با کدام دستور یا به عبارت بهتر مرتبط با کدام تعریف است.  
  </p>
  <p dir='rtl' align='right'>
**بخش 7:** در این بخش نیز گراف جریان کنترلی نمایش داده می‌شود.  
  </p>
  <p dir='rtl' align='right'>
در زیر به صورت بسیار مختصر وظیفه هر بخش توضیح داده شده است.  
  </p>
  
| کلاس | توضیحات |  
| --- | --- |  
| JavaGrammar | این کلاس، کلاس اصلی برنامه بوده و اجرای برنامه از این کلاس شروع می‌شود.در این کلاس رشته دریافتی از فایل ورودی به دنباله‌ای از کاراکترها تبدیل شده و تحویل تحلیل‌گر لغوی می‌شود،تحلیل‌گر لغوی توکن‌ها را شناسایی کرده و دنباله‌ای از توکن‌ها را ایجاد می‌کند،دنباله توکن‌های ایجاد شده تحویل پارسر شده و نتیجه پارس از قانون شروع گرامر در یک درخت ریخته می‌شود،مجموعه دستورات کدمیانی تولید شده در پارسر دریافت شده و تحویل کلاس شناسایی Leader ها می‌شود،کلاس شناسایی و استخراج Leader ها، دستورات Leader را شناسایی کرده و لیستی از index و خود Leader ها را استخراج می‌کندبه وسیله Leader های شناسایی شده بلاک‌های پایه توسط کلاس استخراج کننده بلاک پایه شناسایی و استخراج می‌شودمجموعه بلاک‌های پایه در قالب یک لیست تحویل کلاس تشخیص جریان کنترلی شده و نتیجه یک cfg ایجاد می‌شودcfg ایجاد شده تحویل کلاس تصویرگر شده و تصویر cfg مربوطه تولید می‌شود. |  
| IntermediateScheme | این کلاس یک ساختار برای ذخیره سازی مناسب کدمیانی جهت این برنامه ارائه می‎‌دهد. به دلیل انواع مختلط دستورات در کدمیانی برای هر نوع دستور یک کلاس ایجاد شده که دارای دید یکسان هستند. |  
| IScheme | این interface یک دید یکسان برای انواع مختلف دستورات جهت استفاده آسان در کلاس‌های بالاتر می‌باشد. |  
| AssignmentScheme | این کلاس جهت ذخیره سازی یک دستور انتصاب در کد میانی ایجاد شده است. |  
| ExpressionScheme | این کلاس جهت ذخیره سازی یک دستور محاسباتی در کدمیانی ایجاد گردیده است. |  
| GoToScheme | این کلاس جهت ذخیره سازی یک دستور پرش غیرشرطی ایجاد شده است. |  
| IFStatementScheme | این کلاس جهت ذخیره سازی یک دستور شرطی ایجاد شده است. |  
| LeadersExtractor | این کلاس وظیفه شناسایی Leader ها بر اساس الگوریتم مطرح شده در کلاس ایجاد شده است. |  
| Block | این کلاس ساختاری جهت ذخیره یک بلاک پایه فراهم می‌آورد. |  
| BlocksExtractor | این کلاس وظیفه شناسایی و استخراج بلاک‌های پایه را دارد. الگوریتم این بخش در کلاس درسی مطرح گردیده است. |  
| ControlFlowGraph | این کلاس وظیفه تشخیص جریان کنترلی بین بلاک‌های پایه را دارد. |  
| Visualizer | این کلاس بر اساس کتابخانه‌های افزوده شده تصویر جریان کنترلی را ایجا می‌کند. |  
| BitVector | این کلاس یک بیت از بیت وکتور را شبیه سازی و نگهداری می‌کند. |  
| BitVectors | این کلاس عمکلرد بیت وکتور را شبیه سازی می‌کند. |  
| ReachingDefs | این کلاس الگوریتم Reaching Definitions را پیاده سازی می‌کند. |  
| ReachingDefsForm | این کلاس وظیفه نمایش فرم و اطلاعات در قالب فرم را دارد. |  
  
در کلاس IntermediateScheme انواع تحویل به صورت زیر در درون گرامر مشخص شده است.  
  
| Identifier | Type |  
| --- | --- |  
| i | If Statement |  
| g | Go to |  
| a | Assignment |  
| e | Expression |  
| l | Empty label |  
  
  
 
  
  
  
  
  
[https://jgrapht.org/](https://jgrapht.org/)  
  
[https://github.com/jgraph/mxgraph](https://github.com/jgraph/mxgraph)