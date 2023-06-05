package xyz.wavey.vehicleservice.base.config;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import xyz.wavey.vehicleservice.model.DatabaseType;

@RequiredArgsConstructor
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        DatabaseType databaseType;
        boolean isCurrentTransactionReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        if(isCurrentTransactionReadOnly){
            databaseType = DatabaseType.SLAVE;
        }else{
            databaseType = DatabaseType.MASTER;
        }
        return databaseType;
    }
}