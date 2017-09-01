package org.apache.tinkerpop.gremlin.orientdb;

import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.apache.tinkerpop.gremlin.structure.util.AbstractTransaction;

import java.util.function.Consumer;

/**
 * Created by Enrico Risa on 30/08/2017.
 */
public class OrientStandardTransaction extends AbstractTransaction {

  private OrientStandardGraph g;

  public OrientStandardTransaction(OrientStandardGraph graph) {
    super(graph);
    this.g = graph;
  }

  @Override
  protected void doOpen() {
    tx().doOpen();
  }

  @Override
  protected void doCommit() throws AbstractTransaction.TransactionException {
    tx().doCommit();
  }

  @Override
  protected void doRollback() throws TransactionException {
    tx().doRollback();

  }

  @Override
  protected void fireOnCommit() {
    tx().fireOnCommit();
  }

  @Override
  protected void fireOnRollback() {
    tx().fireOnRollback();
  }

  @Override
  protected void doReadWrite() {
    tx().doReadWrite();
  }

  @Override
  protected void doClose() {
    tx().doClose();
  }

  @Override
  public Transaction onReadWrite(Consumer<Transaction> consumer) {
    return tx().onReadWrite(consumer);
  }

  @Override
  public Transaction onClose(Consumer<Transaction> consumer) {
    return tx().onClose(consumer);
  }

  @Override
  public void addTransactionListener(Consumer<Status> listener) {
    tx().addTransactionListener(listener);
  }

  @Override
  public void removeTransactionListener(Consumer<Status> listener) {
    tx().removeTransactionListener(listener);
  }

  @Override
  public void clearTransactionListeners() {
    tx().clearTransactionListeners();
  }

  @Override
  public boolean isOpen() {
    return tx().isOpen();
  }

  private OrientTransaction tx() {
    return g.graph().tx();
  }
}
