package felipemcassiano.fainansu.services;

import org.springframework.stereotype.Service;

@Service
public class ConverterService {
    public Long amountToLong(Double value) {
        Double  d = (value * 100 );
        return d.longValue();
    }

    public Double amountToDouble(Long value) {
        return (value.doubleValue() / 100.0);
    }
}
