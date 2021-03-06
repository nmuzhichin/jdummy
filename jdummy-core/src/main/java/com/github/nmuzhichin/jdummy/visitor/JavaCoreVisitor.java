package com.github.nmuzhichin.jdummy.visitor;

import com.github.nmuzhichin.jdummy.ModifierAccessible;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.concurrent.ThreadLocalRandom;

final class JavaCoreVisitor extends AbstractMetaValueVisitor {

    private final ModifierAccessible modifierAccessible;

    JavaCoreVisitor(MetaValue type) {
        super(type);
        this.modifierAccessible = JdummyContext.currentValueModifiers();
    }

    @Override
    public void visitType(TypeElement element) {

        var type = element.getUnderlying();
        var packageName = type.getPackageName();
        if (CharSequence.class.isAssignableFrom(type)) {
            visitAsStringType();
        } else if (packageName.startsWith("java.time")
                || Timestamp.class.isAssignableFrom(type)
                || Date.class.isAssignableFrom(type)) {
            visitAsTimeType(type);
        } else if (packageName.startsWith("java.math")) {
            visitAsMathType(type);
        }
    }

    private void visitAsStringType() {
        var value = modifierAccessible.modifyByType(String.class, metaValue.getMeta())
                .orElse("Hello, I'm Jdummy");
        metaValue.setValue(value);
    }

    private void visitAsTimeType(Class<?> type) {

        if (Clock.class.isAssignableFrom(type)) {
            metaValue.setValue(Clock.systemUTC());
        } else if (Instant.class.isAssignableFrom(type)) {
            metaValue.setValue(Instant.now());
        } else if (LocalDate.class.isAssignableFrom(type)) {
            metaValue.setValue(LocalDate.now());
        } else if (LocalDateTime.class.isAssignableFrom(type)) {
            metaValue.setValue(LocalDateTime.now());
        } else if (LocalTime.class.isAssignableFrom(type)) {
            metaValue.setValue(LocalTime.now());
        } else if (MonthDay.class.isAssignableFrom(type)) {
            metaValue.setValue(MonthDay.now());
        } else if (OffsetDateTime.class.isAssignableFrom(type)) {
            metaValue.setValue(OffsetDateTime.now());
        } else if (OffsetTime.class.isAssignableFrom(type)) {
            metaValue.setValue(OffsetTime.now());
        } else if (Year.class.isAssignableFrom(type)) {
            metaValue.setValue(Year.now());
        } else if (YearMonth.class.isAssignableFrom(type)) {
            metaValue.setValue(YearMonth.now());
        } else if (ZonedDateTime.class.isAssignableFrom(type)) {
            metaValue.setValue(ZonedDateTime.now());
        } else if (DayOfWeek.class.isAssignableFrom(type)) {
            metaValue.setValue(DayOfWeek.of(42));
        } else if (Month.class.isAssignableFrom(type)) {
            metaValue.setValue(Month.of(4));
        } else if (ZoneId.class.isAssignableFrom(type)) {
            metaValue.setValue(ZoneId.of("-12"));
        } else if (Period.class.isAssignableFrom(type)) {
            metaValue.setValue(Period.ofDays(4));
        } else if (Duration.class.isAssignableFrom(type)) {
            metaValue.setValue(Duration.ofHours(42));
        } else if (ZoneOffset.class.isAssignableFrom(type)) {
            metaValue.setValue(ZoneOffset.ofHours(12));
        } else if (Timestamp.class.isAssignableFrom(type)) {
            metaValue.setValue(Timestamp.from(Instant.now()));
        } else if (Date.class.isAssignableFrom(type)) {
            metaValue.setValue(Date.from(Instant.now()));
        }
    }

    private void visitAsMathType(Class<?> type) {

        if (BigInteger.class.isAssignableFrom(type)) {
            metaValue.setValue(BigInteger.valueOf(ThreadLocalRandom.current().nextLong()));
        } else if (BigDecimal.class.isAssignableFrom(type)) {
            metaValue.setValue(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble()));
        }
    }
}
