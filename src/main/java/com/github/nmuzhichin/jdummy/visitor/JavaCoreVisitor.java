package com.github.nmuzhichin.jdummy.visitor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.*;
import java.util.concurrent.ThreadLocalRandom;

final class JavaCoreVisitor extends AbstractMetaValueVisitor {

    JavaCoreVisitor(MetaValueType type) {
        super(type);
    }

    @Override
    public void visitType(Class<?> type) {

        var packageName = type.getPackageName();
        if (CharSequence.class.isAssignableFrom(type)) {
            visitAsStringType();
        } else if (packageName.startsWith("java.time")
                || Timestamp.class.isAssignableFrom(type)) {
            visitAsTimeType(type);
        } else if (packageName.startsWith("java.math")) {
            visitAsMathType(type);
        }
    }

    private void visitAsStringType() {
        // todo use field meta for info about string and try generate
        // todo dummy-text more smart
        valueHolder.setValue("Hello, I'm Jdummy");
    }

    private void visitAsTimeType(Class<?> type) {

        if (Clock.class.isAssignableFrom(type)) {
            valueHolder.setValue(Clock.systemUTC());
        } else if (Instant.class.isAssignableFrom(type)) {
            valueHolder.setValue(Instant.now());
        } else if (LocalDate.class.isAssignableFrom(type)) {
            valueHolder.setValue(LocalDate.now());
        } else if (LocalDateTime.class.isAssignableFrom(type)) {
            valueHolder.setValue(LocalDateTime.now());
        } else if (LocalTime.class.isAssignableFrom(type)) {
            valueHolder.setValue(LocalTime.now());
        } else if (MonthDay.class.isAssignableFrom(type)) {
            valueHolder.setValue(MonthDay.now());
        } else if (OffsetDateTime.class.isAssignableFrom(type)) {
            valueHolder.setValue(OffsetDateTime.now());
        } else if (OffsetTime.class.isAssignableFrom(type)) {
            valueHolder.setValue(OffsetTime.now());
        } else if (Year.class.isAssignableFrom(type)) {
            valueHolder.setValue(Year.now());
        } else if (YearMonth.class.isAssignableFrom(type)) {
            valueHolder.setValue(YearMonth.now());
        } else if (ZonedDateTime.class.isAssignableFrom(type)) {
            valueHolder.setValue(ZonedDateTime.now());
        } else if (DayOfWeek.class.isAssignableFrom(type)) {
            valueHolder.setValue(DayOfWeek.of(42));
        } else if (Month.class.isAssignableFrom(type)) {
            valueHolder.setValue(Month.of(4));
        } else if (ZoneId.class.isAssignableFrom(type)) {
            valueHolder.setValue(ZoneId.of("-12"));
        } else if (Period.class.isAssignableFrom(type)) {
            valueHolder.setValue(Period.ofDays(4));
        } else if (Duration.class.isAssignableFrom(type)) {
            valueHolder.setValue(Duration.ofHours(42));
        } else if (ZoneOffset.class.isAssignableFrom(type)) {
            valueHolder.setValue(ZoneOffset.ofHours(12));
        } else if (Timestamp.class.isAssignableFrom(type)) {
            valueHolder.setValue(Timestamp.valueOf(LocalDateTime.now()));
        }
    }

    private void visitAsMathType(Class<?> type) {

        if (BigInteger.class.isAssignableFrom(type)) {
            valueHolder.setValue(BigInteger.valueOf(ThreadLocalRandom.current().nextLong()));
        } else if (BigDecimal.class.isAssignableFrom(type)) {
            valueHolder.setValue(BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble()));
        }
    }
}
