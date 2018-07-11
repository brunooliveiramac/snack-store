package com.startup.domain;

import java.io.Serializable;

/**
 * The generic entity.
 *
 */
public interface Entity<T, K> extends Serializable {

  /**
   *
   * @return
   */
  K identity();

  /**
   * Entities compare by identity, not by attributes.
   *
   * @param other The other entity.
   * @return true if the identities are the same, regardles of other attributes.
   */
  boolean sameIdentityAs(T other);

}
