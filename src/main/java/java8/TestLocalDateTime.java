package java8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

import static java.time.temporal.ChronoField.DAY_OF_WEEK;

public class TestLocalDateTime {
    //1.LocalDate 2.LocalTime 3.LocalDateTime
    @Test
    public void testLocalDateTime(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.of(2019,12,30,10,30,50);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.plusYears(2);
        System.out.println(ldt3);

        System.out.println(ldt.getMonthValue());
    }

    //instant 时间戳
    @Test
    public void testInstant(){
        Instant ins = Instant.now();//默认获取UTC时区
        System.out.println(ins);

        OffsetDateTime offsetDateTime = ins.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        System.out.println(ins.toEpochMilli());//毫秒值
    }

    //Duration 计算2个时间之间的间隔
    //Period 计算2个日期之间的间隔
    @Test
    public void testDuration(){
        Instant ins1 = Instant.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant ins2 = Instant.now();

        Duration duration = Duration.between(ins1, ins2);
        System.out.println(duration.toMillis());
        System.out.println("------------------------");

        LocalDate ld1 = LocalDate.of(2020,4, 13);
        LocalDate ld2 = LocalDate.now();
        System.out.println(Period.between(ld1,ld2).getDays());
    }

    //TemporalAdjuster 时间校正器
    @Test
    public void testTemporalAdjuster(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);//指定日期
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));//TemporalAdjusters实现时间校正的工具类
        System.out.println(ldt3);

        //自定义下个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;
            DayOfWeek dayOfWeek = ldt4.getDayOfWeek();
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                return ldt4.plusDays(3);
            } else if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                return ldt4.plusDays(2);
            } else {
                return ldt4.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }

    //DateTimeFormatter 格式化日期/时间
    @Test
    public void testDateTimeFormatter(){
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ld = LocalDateTime.now();
        String format = ld.format(dtf);
        System.out.println(format);

        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String format1 = dtf2.format(ld);
        System.out.println(format1);

        LocalDateTime parse = ld.parse(format1, dtf2);
        System.out.println(parse);
    }

    @Test
    public void test(){
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        availableZoneIds.forEach(System.out::println);
    }

    //ZoneDate ZoneTime ZoneDateTime
    @Test
    public void testZoneDate(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Australia/ACT"));//指定时区构建时间
        System.out.println(ldt);

        LocalDateTime ldt2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt2);
        ZonedDateTime zonedDateTime = ldt2.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime);
    }

}
