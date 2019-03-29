package data;

import config.HibernateConfig;
import entities.TrxEntity;
import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

public class DataTrx {

    // get session factory connection (replacement DBConnection)
    private static SessionFactory factory = new HibernateConfig().getSessionFactory();

    public List<TrxEntity> getListTrans(Long acn){
        // open new session
        Session sesn = factory.openSession();

        // create temp variable for list user as array list
        List<TrxEntity> trxEntities = new ArrayList<TrxEntity>();
        try {
            // try to execute query and insert result to users list
            Query query = sesn.createQuery("FROM Transaction WHERE acn_debet= :debt OR  acn_credit= :crdt");
            query.setParameter("debt", acn);
            query.setParameter("crdt", acn);
            trxEntities = query.list();
        } catch (HibernateException e){
            e.printStackTrace();
        } finally {
            sesn.close();
        }
        return trxEntities;

    }

    public boolean transfer(TrxEntity trxEntity){
        Session sesn = factory.openSession();
        boolean isAdded = false;
        try {
            Transaction trx = sesn.beginTransaction();
            sesn.save(trxEntity);
            trx.commit();
            isAdded = true;
        } catch(Exception sqlException) {
            if(sesn.getTransaction()!=null) {
                sesn.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            sesn.close();
        }
        return isAdded;
    }

}
